package com.brody.forumgabontunisie.mappers.implementation;

import com.brody.forumgabontunisie.dtos.InscriptionDTO;
import com.brody.forumgabontunisie.entities.Inscription;
import com.brody.forumgabontunisie.mappers.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public Inscription fromInscriptionDTO(InscriptionDTO i) {
        try{
            return Inscription.builder()
                    .email(i.email())
                    .enterprise(i.enterprise())
                    .firstname(i.firstname())
                    .gender(i.gender())
                    .id(i.id())
                    .name(i.name())
                    .phone(i.phone())
                    .profession(i.profession())
                    .website(i.website())
                    .secteur(i.secteur())
                    .type(i.type())
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public InscriptionDTO fromInscription(Inscription i) {
        try{
            return new InscriptionDTO(
                    i.getId(),
                    i.getFirstname(),
                    i.getName(),
                    i.getProfession(),
                    i.getEnterprise(),
                    i.getWebsite(),
                    i.getSecteur(),
                    i.getGender(),
                    i.getEmail(),
                    i.getPhone(),
                    i.getType()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<InscriptionDTO> fromListOfInscriptions(List<Inscription> inscriptions) {
        try{
            return inscriptions.stream().map(this::fromInscription).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
