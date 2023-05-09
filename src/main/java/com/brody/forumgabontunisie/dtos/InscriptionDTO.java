package com.brody.forumgabontunisie.dtos;

public record InscriptionDTO(
        Long id,
        String firstname,
        String name,
        String profession,
        String enterprise,
        String website,
        String secteur,
        String gender,
        String email,
        String phone,
        String type) { }
