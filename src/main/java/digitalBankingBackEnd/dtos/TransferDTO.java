package digitalBankingBackEnd.dtos;


import lombok.Data;


@Data

public class TransferDTO {
    private String compteSource;
    private String CompteDestination;
    private double montantOp;
    private String description;


    }
