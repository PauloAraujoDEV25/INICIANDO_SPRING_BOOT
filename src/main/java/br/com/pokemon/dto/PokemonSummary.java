package br.com.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonSummary {
    private Long id;
    private Integer idPokeApi;
    private String name;
    private String types;
    private LocalDateTime cachedAt;
}
