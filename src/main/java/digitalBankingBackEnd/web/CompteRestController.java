package digitalBankingBackEnd.web;

import digitalBankingBackEnd.dtos.CompteDTO;
import digitalBankingBackEnd.dtos.CreditDTO;
import digitalBankingBackEnd.dtos.DebitDTO;
import digitalBankingBackEnd.dtos.TransferDTO;
import digitalBankingBackEnd.exceptions.CompteException;
import digitalBankingBackEnd.exceptions.SoldeException;
import digitalBankingBackEnd.services.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CompteRestController {
    private BankService bankService;

    @GetMapping("/comptes")
    public List<CompteDTO>listComptes(){
        return bankService.ListComptes();
    }

    @GetMapping("/comptes/{compteId}")
    public CompteDTO getCompte(@PathVariable String compteId) throws CompteException {
        return bankService.getCompte(compteId);
    }

    @PostMapping("/comptes/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws SoldeException, CompteException {
        this.bankService.debit(debitDTO.getCompteId(),debitDTO.getMontantOp(),debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/comptes/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws CompteException {
        this.bankService.credit(creditDTO.getCompteId(),creditDTO.getMontantOp(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/comptes/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO) throws SoldeException, CompteException {
        this.bankService.transfer(
                transferDTO.getCompteSource(),
                transferDTO.getCompteDestination(),
                transferDTO.getMontantOp()
        );
    }

}
