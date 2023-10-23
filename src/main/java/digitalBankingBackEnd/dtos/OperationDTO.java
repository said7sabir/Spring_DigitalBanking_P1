package digitalBankingBackEnd.dtos;

import digitalBankingBackEnd.entities.Compte;
import digitalBankingBackEnd.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class OperationDTO {
    private Long id;
    private String descreption;
    private double MontantOp;
    private Date dateOperation;
    private TypeOperation type;
}
