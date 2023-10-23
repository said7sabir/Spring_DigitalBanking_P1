package digitalBankingBackEnd.repositories;

import digitalBankingBackEnd.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Double> {
}
