package com.gestock.fourniture.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorieDto {
    private Long id;
    private String codeCat;
    private String nomCat;
    private String descriptionCat;
}
