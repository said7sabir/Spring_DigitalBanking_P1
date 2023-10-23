package digitalBankingBackEnd.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {
    private double taux;
}
