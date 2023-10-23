package digitalBankingBackEnd.entities;

import digitalBankingBackEnd.enums.TypeOperation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Operation {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descreption;
    private double MontantOp;
@Temporal(TemporalType.DATE)
    private Date dateOperation;
@Enumerated(EnumType.STRING)
    private TypeOperation type;
@ManyToOne
    private Compte compte;
}
