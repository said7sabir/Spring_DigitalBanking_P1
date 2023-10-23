package digitalBankingBackEnd.services;

import digitalBankingBackEnd.dtos.ClientDTO;
import digitalBankingBackEnd.dtos.CompteDTO;
import digitalBankingBackEnd.dtos.CompteEpargneDTO;
import digitalBankingBackEnd.dtos.CompteRetraitDTO;
import digitalBankingBackEnd.entities.*;
import digitalBankingBackEnd.enums.StatusCompte;
import digitalBankingBackEnd.enums.TypeOperation;
import digitalBankingBackEnd.exceptions.ClientException;
import digitalBankingBackEnd.exceptions.CompteException;
import digitalBankingBackEnd.exceptions.SoldeException;
import digitalBankingBackEnd.mappers.BankMappers;
import digitalBankingBackEnd.repositories.ClientRepository;
import digitalBankingBackEnd.repositories.CompteRepository;
import digitalBankingBackEnd.repositories.OperationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
@Transactional
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private ClientRepository clientRepository;
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;
    private BankMappers mappersImpl;
    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client=mappersImpl.fromClientDTO(clientDTO);
        Client clientSaved=clientRepository.save(client);
        return mappersImpl.fromClient(clientSaved);
    }

    @Override
    public List<ClientDTO> ListClients() {
        List<Client> clients=clientRepository.findAll();
        List<ClientDTO>clientDTOS=clients.stream().toList()
                .stream().map(client -> mappersImpl.fromClient(client))
                .collect(Collectors.toList());
        return clientDTOS;
    }

    @Override
    public ClientDTO getClient(double clientId) throws ClientException {
        Client client=clientRepository.findById(clientId)
                .orElseThrow(()->new ClientException("Client n'existe pas !"));
        return mappersImpl.fromClient(client);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client client =mappersImpl.fromClientDTO(clientDTO);
        Client savedClient=clientRepository.save(client);
        return mappersImpl.fromClient(savedClient);
    }

    @Override
    public void deleteClient(double clientId) {
        clientRepository.deleteById(clientId);
    }

   /* @Override
        public List<ClientDTO> searchClient(String keyword) {
        List<Client> clients=clientRepository.searchClient(keyword);
        List<ClientDTO>clientDTOS=clients.stream()
                .map(client->mappersImpl.fromClient(client))
                .collect(Collectors.toList());
        return clientDTOS;
    }*/


    //ComptesServices
    @Override
    public List<CompteDTO> ListComptes() {
        List<Compte> comptes=compteRepository.findAll();
        List<CompteDTO> compteDTOS=comptes.stream().map(compte -> {
            if (compte instanceof CompteRetrait){
                CompteRetrait compteRetrait=(CompteRetrait) compte;
                return mappersImpl.fromCompteRetrait(compteRetrait);
            }else{
                CompteEpargne compteEpargne=(CompteEpargne) compte;
                return mappersImpl.fromCompteEpargne(compteEpargne);
            }
        }).collect(Collectors.toList());
        return compteDTOS;
    }

    @Override
    public CompteDTO getCompte(String CompteId) throws CompteException {
        Compte compte =compteRepository.findById(CompteId)
                .orElseThrow(()->new CompteException("Compte n'existe pas !"));
        if (compte instanceof CompteRetrait){
            CompteRetrait compteRetrait=(CompteRetrait) compte;
            return mappersImpl.fromCompteRetrait(compteRetrait);
        }else{
            CompteEpargne compteEpargne=(CompteEpargne) compte;
            return  mappersImpl.fromCompteEpargne(compteEpargne);

        }

    }

    @Override
    public CompteEpargneDTO saveCompteEpargne(double taux, double clientId) throws ClientException {

        Client client=clientRepository.findById(clientId).orElse(null);
        if(client==null)
            throw new ClientException("Le Client n'existe pas !");
        CompteEpargne compteEpargne=new CompteEpargne();
        compteEpargne.setId(UUID.randomUUID().toString());
        compteEpargne.setDateCreation(new Date());
        compteEpargne.setTaux(taux);
        compteEpargne.setStatus(StatusCompte.CREATION);
        compteEpargne.setSolde(Math.random()*70000);
        compteEpargne.setClient(client);
        CompteEpargne savedEpargne = compteRepository.save(compteEpargne);
        return mappersImpl.fromCompteEpargne(savedEpargne);
    }

    @Override
    public CompteRetraitDTO saveCompteRetrait(double decouvert, double clientId) throws ClientException {
        Client client=clientRepository.findById(clientId).orElse(null);
        if(client==null)
            throw new ClientException("Le Client n'existe pas");
        CompteRetrait compteRetrait=new CompteRetrait();
        compteRetrait.setId(UUID.randomUUID().toString());
        compteRetrait.setDateCreation(new Date());
        compteRetrait.setStatus(StatusCompte.ACTIVATION);
        compteRetrait.setSolde(Math.random()*80000);
        compteRetrait.setDecouvert(Math.random()*3000);
        compteRetrait.setClient(client);
        CompteRetrait savedRetrait=compteRepository.save(compteRetrait);
        return  mappersImpl.fromCompteRetrait(savedRetrait);

    }

    @Override
    public void debit(String compteId, double montantOp, String description) throws CompteException, SoldeException {
        Compte compte=compteRepository.findById(compteId)
                .orElseThrow(()->new CompteException("Compte n'existe pas !"));
        if(compte.getSolde()<montantOp)
            throw new SoldeException("Solde insufisant !");
        Operation operation =new Operation();
        operation.setType(TypeOperation.DEBIT);
        operation.setDateOperation(new Date());
        operation.setMontantOp(montantOp);
        operation.setDescreption("operation debit");
        operation.setCompte(compte);
        operationRepository.save(operation);
        compte.setSolde(compte.getSolde()-montantOp);
        compteRepository.save(compte);

    }

    @Override
    public void credit(String compteId, double montantOp, String description) throws CompteException {
        Compte compte=compteRepository.findById(compteId)
                .orElseThrow(()->new CompteException("Compte n'existe pas !"));

        Operation operation =new Operation();
        operation.setType(TypeOperation.CREDIT);
        operation.setDateOperation(new Date());
        operation.setMontantOp(montantOp);
        operation.setDescreption("operation credit");
        operation.setCompte(compte);
        operationRepository.save(operation);
        compte.setSolde(compte.getSolde()+montantOp);
        compteRepository.save(compte);
    }

    @Override
    public void transfer(String compteSource, String compteDestination, double montantOp) throws SoldeException, CompteException {
        debit(compteSource, montantOp, "transfer vers : "+compteDestination);
        credit(compteDestination, montantOp, "transfer de : "+compteSource);
    }
}
