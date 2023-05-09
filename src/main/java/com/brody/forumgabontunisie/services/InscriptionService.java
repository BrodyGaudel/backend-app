package com.brody.forumgabontunisie.services;

import com.brody.forumgabontunisie.dtos.ContactDTO;
import com.brody.forumgabontunisie.dtos.InscriptionDTO;
import com.brody.forumgabontunisie.exceptions.InscriptionNotFound;

import java.util.List;

public interface InscriptionService {
    InscriptionDTO saveInscription(InscriptionDTO inscriptionDTO);
    InscriptionDTO getInscriptionById(Long id) throws InscriptionNotFound;
    List<InscriptionDTO> getAllInscription();
    List<InscriptionDTO> searchInscription(String keyword);
    void deleteInscriptionById(Long id);

    ContactDTO contact(ContactDTO contactDTO);
}
