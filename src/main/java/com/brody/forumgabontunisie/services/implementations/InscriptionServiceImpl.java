package com.brody.forumgabontunisie.services.implementations;

import com.brody.forumgabontunisie.dtos.ContactDTO;
import com.brody.forumgabontunisie.dtos.InscriptionDTO;
import com.brody.forumgabontunisie.entities.Inscription;
import com.brody.forumgabontunisie.exceptions.InscriptionNotFound;
import com.brody.forumgabontunisie.mappers.Mappers;
import com.brody.forumgabontunisie.repositories.InscriptionRepository;
import com.brody.forumgabontunisie.services.InscriptionService;
import com.brody.forumgabontunisie.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class InscriptionServiceImpl implements InscriptionService {

    private static final String INSCRIPTION_FOUND = "inscription(s) found";
    private static final String INSCRIPTION_NOT_FOUND = "inscription(s) not found";

    private final InscriptionRepository inscriptionRepository;
    private final Mappers mappers;
    private final MailService mailService;

    public InscriptionServiceImpl(InscriptionRepository inscriptionRepository, Mappers mappers, MailService mailService) {
        this.inscriptionRepository = inscriptionRepository;
        this.mappers = mappers;
        this.mailService = mailService;
    }

    @Override
    public InscriptionDTO saveInscription(InscriptionDTO inscriptionDTO) {
        Boolean emailExist = inscriptionRepository.verifyIfEmailExists(inscriptionDTO.email());
        Boolean phoneExist = inscriptionRepository.verifyIfPhoneExists(inscriptionDTO.phone());
        if(Boolean.TRUE.equals(emailExist) || Boolean.TRUE.equals(phoneExist)){
            log.warn("inscription not saved cause email or phone already taken by another user");
            return null;
        }else{
           try{
               Inscription inscription = mappers.fromInscriptionDTO(inscriptionDTO);
               inscription.setDate(new Date());
               Inscription savedInscription = inscriptionRepository.save(inscription);
               log.info("inscription saved");
               return mappers.fromInscription(savedInscription);
           }catch (Exception e){
               log.error("inscription not saved due to :"+e.getMessage());
               return null;
           }
        }
    }

    @Override
    public InscriptionDTO getInscriptionById(Long id) throws InscriptionNotFound {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow( () -> new InscriptionNotFound(INSCRIPTION_NOT_FOUND));
        log.info(INSCRIPTION_FOUND);
        return mappers.fromInscription(inscription);
    }

    @Override
    public List<InscriptionDTO> getAllInscription() {
        try{
            List<Inscription> inscriptions = inscriptionRepository.findAll();
            log.info(INSCRIPTION_FOUND);
            return mappers.fromListOfInscriptions(inscriptions);
        }catch (Exception e){
            log.error(INSCRIPTION_NOT_FOUND);
            return Collections.emptyList();
        }
    }

    @Override
    public List<InscriptionDTO> searchInscription(String keyword) {
        try{
            List<Inscription> inscriptions = inscriptionRepository.search(keyword);
            log.info(INSCRIPTION_FOUND);
            return mappers.fromListOfInscriptions(inscriptions);
        }catch (Exception e){
            log.error(INSCRIPTION_NOT_FOUND);
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteInscriptionById(Long id) {
        try{
            inscriptionRepository.deleteById(id);
            log.info("inscription deleted");
        }catch (Exception e){
            log.error("inscription not deleted");
        }
    }

    @Override
    public ContactDTO contact(ContactDTO contactDTO) {
        try{
            String body = "MESSAGE DE :"+contactDTO.name()
                    +"\n"+"MAIL :"+contactDTO.from()
                    +"\n"+contactDTO.body();
            mailService.sendMail("fmouba@gmail.com", contactDTO.subject(), body);
            log.info("mail sent to admin");
            return contactDTO;
        }catch (Exception e){
            log.error("mail not sent to admin");
            return null;
        }
    }
}
