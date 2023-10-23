package digitalBankingBackEnd.repositories;

import digitalBankingBackEnd.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
