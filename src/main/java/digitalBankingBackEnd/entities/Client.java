package digitalBankingBackEnd.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Collection<Compte> comptes;
}
