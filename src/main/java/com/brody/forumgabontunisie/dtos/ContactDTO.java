package com.brody.forumgabontunisie.dtos;

public record ContactDTO(
        String from,
        String subject,
        String body,
        String name) {
}
