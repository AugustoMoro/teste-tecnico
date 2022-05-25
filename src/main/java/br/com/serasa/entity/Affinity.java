package br.com.serasa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraph(
        name = "Affinity.States",
        attributeNodes = {
                @NamedAttributeNode("states")
        }
)

@Getter
@Setter
@Entity
@Table(name = "affinity")
@EqualsAndHashCode
public class Affinity {
    @Id
    @Column(unique = true, nullable = false)
    private String region;

    @Column(nullable = false)
    @ElementCollection(targetClass = String.class)
    private List<String> states;
}
