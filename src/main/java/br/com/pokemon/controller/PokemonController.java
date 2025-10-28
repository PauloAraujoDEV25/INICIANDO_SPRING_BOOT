package br.com.pokemon.controller;

import br.com.pokemon.dto.FavoriteRequest;
import br.com.pokemon.dto.PokemonResponse;
import br.com.pokemon.dto.PokemonSummary;
import br.com.pokemon.service.PokemonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
@Validated
public class PokemonController {

    private static final Logger logger = LoggerFactory.getLogger(PokemonController.class);
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    /**
     * POST /api/pokemon/cache/{nameOrId}
     * Consulta a PokeAPI e persiste/atualiza no H2
     */
    @PostMapping("/cache/{nameOrId}")
    public ResponseEntity<PokemonResponse> cachePokemon(@PathVariable String nameOrId) {
        logger.info("Requisição para cachear Pokémon: {}", nameOrId);
        PokemonResponse response = pokemonService.cachePokemon(nameOrId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/pokemon?page=0&size=10
     * Retorna listagem paginada
     */
    @GetMapping
    public ResponseEntity<Page<PokemonSummary>> listPokemon(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        logger.debug("Listando Pokémon - página: {}, tamanho: {}", page, size);
        Page<PokemonSummary> pokemonPage = pokemonService.listPokemon(page, size);
        return ResponseEntity.ok(pokemonPage);
    }

    /**
     * GET /api/pokemon/{idLocal}
     * Retorna o registro completo do H2
     */
    @GetMapping("/{idLocal}")
    public ResponseEntity<PokemonResponse> getPokemonById(@PathVariable @Min(1) Long idLocal) {
        logger.debug("Buscando Pokémon por ID: {}", idLocal);
        PokemonResponse response = pokemonService.getPokemonById(idLocal);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/pokemon/search?type={typeName}
     * Filtra Pokémon por tipo (case-insensitive)
     */
    @GetMapping("/search")
    public ResponseEntity<Page<PokemonSummary>> searchByType(
            @RequestParam String type,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        logger.debug("Buscando Pokémon por tipo: {}", type);
        Page<PokemonSummary> pokemonPage = pokemonService.searchByType(type, page, size);
        return ResponseEntity.ok(pokemonPage);
    }

    /**
     * PATCH /api/pokemon/{idLocal}/favorite
     * Atualiza campos favorite e note
     */
    @PatchMapping("/{idLocal}/favorite")
    public ResponseEntity<PokemonResponse> updateFavorite(
            @PathVariable @Min(1) Long idLocal,
            @Valid @RequestBody FavoriteRequest request) {
        logger.info("Atualizando favorito para Pokémon ID: {}", idLocal);
        PokemonResponse response = pokemonService.updateFavorite(idLocal, request);
        return ResponseEntity.ok(response);
    }
}
