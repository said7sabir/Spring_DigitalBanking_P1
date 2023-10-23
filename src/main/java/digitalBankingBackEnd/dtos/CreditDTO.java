package digitalBankingBackEnd.dtos;


import lombok.Data;


@Data

public class CreditDTO {
    private String compteId;
    private String description;
    private double montantOp;


    }
