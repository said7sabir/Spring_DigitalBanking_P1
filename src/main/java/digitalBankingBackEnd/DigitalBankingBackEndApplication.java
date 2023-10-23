package digitalBankingBackEnd;

import digitalBankingBackEnd.entities.Client;
import digitalBankingBackEnd.entities.CompteEpargne;
import digitalBankingBackEnd.entities.CompteRetrait;
import digitalBankingBackEnd.entities.Operation;
import digitalBankingBackEnd.enums.StatusCompte;
import digitalBankingBackEnd.enums.TypeOperation;
import digitalBankingBackEnd.repositories.ClientRepository;
import digitalBankingBackEnd.repositories.CompteRepository;
import digitalBankingBackEnd.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingBackEndApplication.class, args);
	}

@Bean
	CommandLineRunner commandLineRunner(ClientRepository clientRepository,
										CompteRepository compteRepository,
										OperationRepository operationRepository){
		return  args -> {
			Stream.of("Hassan","Ali", "Med", "Hicham").forEach(name->{
				Client client = new Client();
				client.setNom(name);
				client.setEmail(name+"@gamil.com");
				clientRepository.save(client);
			});


		clientRepository.findAll().forEach(client -> {
			CompteRetrait compteRetrait=new CompteRetrait();
			compteRetrait.setId(UUID.randomUUID().toString());
			compteRetrait.setStatus(Math.random()>0.5?StatusCompte.CREATION:StatusCompte.ACTIVATION);
			compteRetrait.setDateCreation(new Date());
			compteRetrait.setSolde(Math.random()*50000);
			compteRetrait.setDecouvert(16000);
			compteRetrait.setClient(client);
			compteRepository.save(compteRetrait);
		});

		clientRepository.findAll().forEach(client -> {
			CompteEpargne compteEpargne=new CompteEpargne();
			compteEpargne.setId(UUID.randomUUID().toString());
			compteEpargne.setStatus(Math.random()>0.5?StatusCompte.SUSPENSION:StatusCompte.CREATION);
			compteEpargne.setDateCreation(new Date());
			compteEpargne.setSolde(Math.random()*50000);
			compteEpargne.setTaux(0.5);
			compteEpargne.setClient(client);
			compteRepository.save(compteEpargne);
		});
			compteRepository.findAll().forEach(compte -> {
				for (int i=0;i<10;i++){
					Operation operation=new Operation();
					operation.setDateOperation(new Date());
					operation.setType(Math.random()>0.5?TypeOperation.CREDIT:TypeOperation.DEBIT);
					operation.setCompte(compte);
					operation.setDescreption("hhh");
					operation.setMontantOp(Math.random()*15000);
					operation.setCompte(compte);
					operationRepository.save(operation);
				}
			});

		};
	}

}
