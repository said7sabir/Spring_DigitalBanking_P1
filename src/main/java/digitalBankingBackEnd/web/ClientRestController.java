package digitalBankingBackEnd.web;


import digitalBankingBackEnd.dtos.ClientDTO;
import digitalBankingBackEnd.exceptions.ClientException;
import digitalBankingBackEnd.services.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientRestController {
    private BankService bankService;


    @GetMapping("/clients")
    public List<ClientDTO>Listclients(){
        return  bankService.ListClients();
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable(name="id")double clientId) throws ClientException {
        return bankService.getClient(clientId);
    }
    @PostMapping("/clients")
    public ClientDTO saveClient(@PathVariable ClientDTO clientDTO){
        return bankService.saveClient(clientDTO);
    }
    @PutMapping("/clients/{clientId}")
    public ClientDTO updateClient(@PathVariable double clientId, @RequestBody ClientDTO clientDTO){
        clientDTO.setId((long) clientId);
        return bankService.updateClient(clientDTO);
    }
    @DeleteMapping("/client/{id}")
    public void deleteClient(@PathVariable double id){
        bankService.deleteClient(id);
    }
    /*@GetMapping("/clients/search")
    public List<ClientDTO>searchClient(@RequestParam(name="keyword", defaultValue = "")String keyword){
        return bankService.searchClient("%"+keyword+"%");
    }*/
}
