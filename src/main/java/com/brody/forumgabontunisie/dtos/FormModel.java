package com.brody.forumgabontunisie.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FormModel {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
