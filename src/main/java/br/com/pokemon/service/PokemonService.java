package br.com.pokemon.service;

import br.com.pokemon.dto.*;
import br.com.pokemon.exception.PokemonNotFoundException;
import br.com.pokemon.model.entity.Pokemon;
import br.com.pokemon.model.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final Logger logger = LoggerFactory.getLogger(PokemonService.class);
    
    private final PokemonRepository pokemonRepository;
    private final PokeApiService pokeApiService;

    public PokemonService(PokemonRepository pokemonRepository, PokeApiService pokeApiService) {
        this.pokemonRepository = pokemonRepository;
        this.pokeApiService = pokeApiService;
    }

    @Transactional
    public PokemonResponse cachePokemon(String nameOrId) {
        logger.info("Iniciando cache de Pokémon: {}", nameOrId);
        
        // Busca da PokeAPI
        PokeApiResponse pokeApiResponse = pokeApiService.getPokemon(nameOrId);

        // Verifica se já existe no banco
        Pokemon pokemon = pokemonRepository.findByIdPokeApi(pokeApiResponse.getId())
                .orElse(new Pokemon());

        // Mapeia dados da PokeAPI para entidade
        pokemon.setIdPokeApi(pokeApiResponse.getId());
        pokemon.setName(pokeApiResponse.getName());
        pokemon.setHeight(pokeApiResponse.getHeight());
        pokemon.setWeight(pokeApiResponse.getWeight());

        // Primeira habilidade
        if (pokeApiResponse.getAbilities() != null && !pokeApiResponse.getAbilities().isEmpty()) {
            pokemon.setAbility(pokeApiResponse.getAbilities().get(0).getAbility().getName());
        }

        // Lista de tipos (CSV)
        if (pokeApiResponse.getTypes() != null && !pokeApiResponse.getTypes().isEmpty()) {
            String types = pokeApiResponse.getTypes().stream()
                    .map(typeWrapper -> typeWrapper.getType().getName())
                    .collect(Collectors.joining(", "));
            pokemon.setTypes(types);
        }

        // Atualiza timestamp de cache
        pokemon.setCachedAt(LocalDateTime.now());

        // Salva no banco
        pokemon = pokemonRepository.save(pokemon);
        
        logger.info("Pokémon '{}' (ID: {}) salvo com sucesso no banco local", 
            pokemon.getName(), pokemon.getId());

        return mapToResponse(pokemon);
    }

    public Page<PokemonSummary> listPokemon(int page, int size) {
        logger.debug("Listando Pokémon - página: {}, tamanho: {}", page, size);
        
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Parâmetros de paginação inválidos");
        }
        
        Pageable pageable = PageRequest.of(page, size);
        return pokemonRepository.findAll(pageable)
                .map(this::mapToSummary);
    }

    public PokemonResponse getPokemonById(Long id) {
        logger.debug("Buscando Pokémon por ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
        
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(id));
                
        return mapToResponse(pokemon);
    }

    public Page<PokemonSummary> searchByType(String type, int page, int size) {
        logger.debug("Buscando Pokémon por tipo: {} - página: {}, tamanho: {}", type, page, size);
        
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo não pode ser vazio");
        }
        
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Parâmetros de paginação inválidos");
        }
        
        Pageable pageable = PageRequest.of(page, size);
        return pokemonRepository.findByTypeContainingIgnoreCase(type, pageable)
                .map(this::mapToSummary);
    }

    @Transactional
    public PokemonResponse updateFavorite(Long id, FavoriteRequest request) {
        logger.info("Atualizando favorito para Pokémon ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
        
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(id));

        if (request.getFavorite() != null) {
            pokemon.setFavorite(request.getFavorite());
            logger.debug("Favorito atualizado para: {}", request.getFavorite());
        }
        if (request.getNote() != null) {
            pokemon.setNote(request.getNote());
            logger.debug("Nota atualizada");
        }

        pokemon = pokemonRepository.save(pokemon);
        logger.info("Pokémon ID {} atualizado com sucesso", id);
        
        return mapToResponse(pokemon);
    }

    private PokemonResponse mapToResponse(Pokemon pokemon) {
        return new PokemonResponse(
                pokemon.getId(),
                pokemon.getIdPokeApi(),
                pokemon.getName(),
                pokemon.getHeight(),
                pokemon.getWeight(),
                pokemon.getAbility(),
                pokemon.getTypes(),
                pokemon.getCachedAt(),
                pokemon.getFavorite(),
                pokemon.getNote()
        );
    }

    private PokemonSummary mapToSummary(Pokemon pokemon) {
        return new PokemonSummary(
                pokemon.getId(),
                pokemon.getIdPokeApi(),
                pokemon.getName(),
                pokemon.getTypes(),
                pokemon.getCachedAt()
        );
    }
}
