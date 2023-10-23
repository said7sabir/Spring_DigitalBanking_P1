package digitalBankingBackEnd.dtos;


import digitalBankingBackEnd.enums.StatusCompte;

import lombok.Data;
import java.util.Date;


@Data

public class CompteEpargneDTO extends CompteDTO {
    private String id;
    private Date dateCreation;
    private StatusCompte status;
    private double solde;
    private ClientDTO clientDTO;
    private double taux;


    }
