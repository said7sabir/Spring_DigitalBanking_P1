package digitalBankingBackEnd.dtos;


import digitalBankingBackEnd.entities.Compte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;

}
