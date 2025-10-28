package br.com.pokemon.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pokemon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Integer idPokeApi;

    @Column(nullable = false)
    private String name;

    private Integer height;

    private Integer weight;

    private String ability;

    @Column(length = 500)
    private String types;

    @Column(nullable = false)
    private LocalDateTime cachedAt;

    @Column(nullable = false)
    private Boolean favorite = false;

    @Column(length = 1000)
    private String note;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (cachedAt == null) {
            cachedAt = LocalDateTime.now();
        }
        if (favorite == null) {
            favorite = false;
        }
    }
}
