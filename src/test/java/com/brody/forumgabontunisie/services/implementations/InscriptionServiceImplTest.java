package com.brody.forumgabontunisie.services.implementations;

import com.brody.forumgabontunisie.dtos.InscriptionDTO;
import com.brody.forumgabontunisie.entities.Inscription;
import com.brody.forumgabontunisie.repositories.InscriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InscriptionServiceImplTest {

    @Autowired
    InscriptionRepository inscriptionRepository;

    @Autowired
    InscriptionServiceImpl service;

    @Test
    void getInscriptionById() {
        String email = UUID.randomUUID().toString();
        String phone = "PHONE"+email;
        Inscription inscription = Inscription.builder()
                .type("TYPE")
                .secteur("SECTEUR")
                .website("WEBSITE")
                .profession("INGENIEUR")
                .name("NAME")
                .firstname("FIRSTNAME")
                .gender("MONSIEUR")
                .enterprise("ENTERPRISE")
                .date(new Date())
                .phone(phone)
                .email(email)
                .build();
        Inscription saved = inscriptionRepository.save(inscription);
        InscriptionDTO inscriptionDTO;
        try{
            inscriptionDTO = service.getInscriptionById(saved.getId());
        }catch (Exception e){
            inscriptionDTO = null;
        }
        assertNotNull(inscriptionDTO);
    }

    @Test
    void getAllInscription() {
        String email = UUID.randomUUID().toString();
        String phone = "PHONE"+email;
        Inscription inscription = Inscription.builder()
                .type("TYPE")
                .secteur("SECTEUR")
                .website("WEBSITE")
                .profession("INGENIEUR")
                .name("NAME")
                .firstname("FIRSTNAME")
                .gender("MONSIEUR")
                .enterprise("ENTERPRISE")
                .date(new Date())
                .phone(phone)
                .email(email)
                .build();
        inscriptionRepository.save(inscription);
        List<InscriptionDTO> inscriptionDTOList = service.getAllInscription();
        InscriptionDTO inscriptionDTO = inscriptionDTOList.get(0);
        assertNotNull(inscriptionDTOList);
        assertNotNull(inscriptionDTO);
    }

    @Test
    void searchInscription() {
        String email = UUID.randomUUID().toString();
        String phone = "PHONE"+email;
        Inscription inscription = Inscription.builder()
                .type("TYPE")
                .secteur("SECTEUR")
                .website("WEBSITE")
                .profession("INGENIEUR")
                .name("NAME")
                .firstname("FIRSTNAME")
                .gender("MONSIEUR")
                .enterprise("ENTERPRISE")
                .date(new Date())
                .phone(phone)
                .email(email)
                .build();
        inscriptionRepository.save(inscription);
        List<InscriptionDTO> inscriptionDTOList = service.searchInscription("NAME");
        InscriptionDTO inscriptionDTO = inscriptionDTOList.get(0);
        assertNotNull(inscriptionDTOList);
        assertNotNull(inscriptionDTO);
    }

    @Test
    void deleteInscriptionById() {
        String email = UUID.randomUUID().toString();
        String phone = "PHONE"+email;
        Inscription inscription = Inscription.builder()
                .type("TYPE")
                .secteur("SECTEUR")
                .website("WEBSITE")
                .profession("INGENIEUR")
                .name("NAME")
                .firstname("FIRSTNAME")
                .gender("MONSIEUR")
                .enterprise("ENTERPRISE")
                .date(new Date())
                .phone(phone)
                .email(email)
                .build();
        Inscription saved = inscriptionRepository.save(inscription);
        service.deleteInscriptionById(saved.getId());
        Inscription i = inscriptionRepository.findById(saved.getId()).orElse(null);
        assertNull(i);
    }


}