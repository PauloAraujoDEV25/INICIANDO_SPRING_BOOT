package br.com.pokemon.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokeApiResponse {
    private Integer id;
    private String name;
    private Integer height;
    private Integer weight;
    private List<AbilityWrapper> abilities;
    private List<TypeWrapper> types;

    @Data
    public static class AbilityWrapper {
        private Ability ability;
        private Boolean is_hidden;
        private Integer slot;
    }

    @Data
    public static class Ability {
        private String name;
        private String url;
    }

    @Data
    public static class TypeWrapper {
        private Integer slot;
        private Type type;
    }

    @Data
    public static class Type {
        private String name;
        private String url;
    }
}
