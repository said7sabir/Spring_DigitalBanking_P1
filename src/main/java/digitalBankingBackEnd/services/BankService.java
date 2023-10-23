package digitalBankingBackEnd.services;

import digitalBankingBackEnd.dtos.ClientDTO;
import digitalBankingBackEnd.dtos.CompteDTO;
import digitalBankingBackEnd.dtos.CompteEpargneDTO;
import digitalBankingBackEnd.dtos.CompteRetraitDTO;
import digitalBankingBackEnd.entities.CompteRetrait;
import digitalBankingBackEnd.exceptions.ClientException;
import digitalBankingBackEnd.exceptions.CompteException;
import digitalBankingBackEnd.exceptions.SoldeException;

import java.util.List;

public interface BankService {

    //Client
    ClientDTO saveClient(ClientDTO clientDTO);
    List<ClientDTO> ListClients();
    ClientDTO getClient(double clientId) throws ClientException;
    ClientDTO updateClient (ClientDTO clientDTO);
    void deleteClient(double clientId);
    //List<ClientDTO>searchClient(String keyword);

    //Compte
    List<CompteDTO> ListComptes();
    CompteDTO getCompte(String CompteId) throws CompteException;

    CompteEpargneDTO saveCompteEpargne(double taux, double clientId) throws ClientException;
    CompteRetraitDTO saveCompteRetrait (double decouvert, double clientId) throws ClientException;

    void debit (String compteId, double montantOp,String description) throws CompteException, SoldeException;
    void credit (String compteId, double montantOp, String description) throws CompteException;
    void transfer(String compteSource, String compteDestination, double montantOp) throws SoldeException, CompteException;
}
