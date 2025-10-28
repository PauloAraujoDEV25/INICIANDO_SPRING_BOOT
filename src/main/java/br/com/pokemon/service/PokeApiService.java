package br.com.pokemon.service;

import br.com.pokemon.dto.PokeApiResponse;
import br.com.pokemon.exception.PokeApiException;
import br.com.pokemon.exception.PokemonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Service
public class PokeApiService {

    private static final Logger logger = LoggerFactory.getLogger(PokeApiService.class);
    private final WebClient webClient;

    public PokeApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public PokeApiResponse getPokemon(String nameOrId) {
        logger.info("Buscando Pokémon '{}' na PokeAPI", nameOrId);
        
        try {
            PokeApiResponse response = webClient.get()
                    .uri("/pokemon/{nameOrId}", nameOrId)
                    .retrieve()
                    .bodyToMono(PokeApiResponse.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();
            
            logger.info("Pokémon '{}' encontrado com sucesso (ID: {})", nameOrId, response.getId());
            return response;
            
        } catch (WebClientResponseException.NotFound e) {
            logger.warn("Pokémon '{}' não encontrado na PokeAPI", nameOrId);
            throw new PokemonNotFoundException(nameOrId, true);
            
        } catch (WebClientResponseException e) {
            logger.error("Erro HTTP {} ao consultar PokeAPI para '{}'", e.getStatusCode(), nameOrId);
            throw new PokeApiException("Erro HTTP " + e.getStatusCode() + " ao consultar PokeAPI", e);
            
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar Pokémon '{}' na PokeAPI", nameOrId, e);
            throw new PokeApiException("Erro ao buscar Pokémon na PokeAPI: " + e.getMessage(), e);
        }
    }
}
