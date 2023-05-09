package com.brody.forumgabontunisie.mappers;

import com.brody.forumgabontunisie.dtos.InscriptionDTO;
import com.brody.forumgabontunisie.entities.Inscription;

import java.util.List;

public interface Mappers {
    Inscription fromInscriptionDTO(InscriptionDTO i);
    InscriptionDTO fromInscription(Inscription i);
    List<InscriptionDTO> fromListOfInscriptions(List<Inscription> inscriptions);
}
