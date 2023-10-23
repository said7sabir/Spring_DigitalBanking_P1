package digitalBankingBackEnd.mappers;

import digitalBankingBackEnd.dtos.ClientDTO;
import digitalBankingBackEnd.dtos.CompteEpargneDTO;
import digitalBankingBackEnd.dtos.CompteRetraitDTO;
import digitalBankingBackEnd.dtos.OperationDTO;
import digitalBankingBackEnd.entities.Client;
import digitalBankingBackEnd.entities.CompteEpargne;
import digitalBankingBackEnd.entities.CompteRetrait;
import digitalBankingBackEnd.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class BankMappers {
    //ClientsDTO
    public ClientDTO fromClient (Client client){
        ClientDTO clientDTO=new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        return clientDTO;
    }

    public Client fromClientDTO (ClientDTO clientDTO){
        Client client=new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }


    //CompteDTO
    public CompteRetraitDTO fromCompteRetrait(CompteRetrait compteRetrait){
        CompteRetraitDTO compteRetraitDTO=new CompteRetraitDTO();
        BeanUtils.copyProperties(compteRetrait,compteRetraitDTO);
        compteRetraitDTO.setClientDTO(fromClient(compteRetrait.getClient()));
        return compteRetraitDTO;
    }
    public CompteRetrait fromCompteRetraitDTO(CompteRetraitDTO compteRetraitDTO){
        CompteRetrait compteRetrait=new CompteRetrait();
        BeanUtils.copyProperties(compteRetraitDTO, compteRetrait);
        compteRetrait.setClient(fromClientDTO(compteRetraitDTO.getClientDTO()));
        return compteRetrait;
    }
    public CompteEpargneDTO fromCompteEpargne(CompteEpargne compteEpargne){
        CompteEpargneDTO compteEpargneDTO=new CompteEpargneDTO();
        BeanUtils.copyProperties(compteEpargne,compteEpargneDTO);
        compteEpargneDTO.setClientDTO(fromClient(compteEpargne.getClient()));
        return compteEpargneDTO;
    }
    public CompteEpargne fromCompteEpargneDTO(CompteEpargneDTO compteEpargneDTO){
        CompteEpargne compteEpargne=new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDTO, compteEpargne);
        compteEpargne.setClient(fromClientDTO(compteEpargneDTO.getClientDTO()));
        return compteEpargne;
    }

    public OperationDTO fromOperation (Operation operation){
        OperationDTO operationDTO=new OperationDTO();
        BeanUtils.copyProperties(operation,operationDTO);
        return operationDTO;
    }
}
