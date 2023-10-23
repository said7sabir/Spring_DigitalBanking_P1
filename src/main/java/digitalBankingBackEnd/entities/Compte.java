package digitalBankingBackEnd.entities;

import digitalBankingBackEnd.enums.StatusCompte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)

public class Compte {
    @Id
    private String id;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private StatusCompte status;
    private double solde;

    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "compte", fetch = FetchType.EAGER)
    private Collection<Operation>operations;
}
