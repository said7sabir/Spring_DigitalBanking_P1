package digitalBankingBackEnd.dtos;

import digitalBankingBackEnd.entities.Client;
import digitalBankingBackEnd.enums.StatusCompte;
import lombok.Data;

import java.util.Date;


@Data

public class CompteRetraitDTO extends CompteDTO{
    private String id;
    private Date dateCreation;
    private StatusCompte status;
    private ClientDTO clientDTO;
    private double solde;
    private double decouvert;

}
