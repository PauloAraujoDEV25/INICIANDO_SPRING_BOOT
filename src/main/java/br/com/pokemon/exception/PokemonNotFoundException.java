package br.com.pokemon.exception;

public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
    
    public PokemonNotFoundException(Long id) {
        super("Pokémon com ID " + id + " não encontrado no banco local");
    }
    
    public PokemonNotFoundException(String nameOrId, boolean fromApi) {
        super("Pokémon '" + nameOrId + "' não encontrado na PokeAPI");
    }
}
