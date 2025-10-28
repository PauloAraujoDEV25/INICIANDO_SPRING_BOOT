package br.com.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponse {
    private Long id;
    private Integer idPokeApi;
    private String name;
    private Integer height;
    private Integer weight;
    private String ability;
    private String types;
    private LocalDateTime cachedAt;
    private Boolean favorite;
    private String note;
}
