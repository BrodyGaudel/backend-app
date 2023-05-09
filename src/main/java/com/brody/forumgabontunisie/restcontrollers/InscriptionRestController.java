package com.brody.forumgabontunisie.restcontrollers;

import com.brody.forumgabontunisie.dtos.ContactDTO;
import com.brody.forumgabontunisie.dtos.InscriptionDTO;
import com.brody.forumgabontunisie.exceptions.InscriptionNotFound;
import com.brody.forumgabontunisie.services.InscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionRestController {

    private final InscriptionService inscriptionService;

    public InscriptionRestController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping("/save")
    @ResponseBody
    public InscriptionDTO saveInscription(@RequestBody InscriptionDTO inscriptionDTO){
        return inscriptionService.saveInscription(inscriptionDTO);
    }

    @PostMapping("/contact")
    @ResponseBody
    public ContactDTO contact(@RequestBody ContactDTO contactDTO){
        return inscriptionService.contact(contactDTO);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public InscriptionDTO getInscriptionById(@PathVariable(name = "id") Long id) throws InscriptionNotFound{
        return inscriptionService.getInscriptionById(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<InscriptionDTO> getAllInscription(){
        return inscriptionService.getAllInscription();
    }

    @GetMapping("/search")
    @ResponseBody
    public List<InscriptionDTO> searchInscription(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return inscriptionService.searchInscription("%"+keyword+"%");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInscriptionById(@PathVariable(name = "id") Long id){
        inscriptionService.deleteInscriptionById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
