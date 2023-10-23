package digitalBankingBackEnd.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
@DiscriminatorValue("CR")
public class CompteRetrait extends Compte {
    private double decouvert;
}
