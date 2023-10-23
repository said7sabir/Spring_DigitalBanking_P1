package digitalBankingBackEnd.dtos;

import digitalBankingBackEnd.entities.Client;
import digitalBankingBackEnd.entities.Operation;
import digitalBankingBackEnd.enums.StatusCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;


@Data

public class CompteDTO {
    private String id;

}
