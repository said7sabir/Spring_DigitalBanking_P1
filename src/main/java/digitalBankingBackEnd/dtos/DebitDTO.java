package digitalBankingBackEnd.dtos;


import digitalBankingBackEnd.enums.StatusCompte;
import lombok.Data;

import java.util.Date;


@Data

public class DebitDTO {
    private String compteId;
    private String description;
    private double montantOp;


    }
