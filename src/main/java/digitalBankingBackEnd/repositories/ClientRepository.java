package digitalBankingBackEnd.repositories;

import digitalBankingBackEnd.entities.Client;
import digitalBankingBackEnd.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Double> {
    //List<Client> searchClient(@Param("kw")String keyword);
}
