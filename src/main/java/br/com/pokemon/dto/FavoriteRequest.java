package br.com.pokemon.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FavoriteRequest {
    private Boolean favorite;
    
    @Size(max = 1000, message = "A nota não pode ter mais de 1000 caracteres")
    private String note;
}
