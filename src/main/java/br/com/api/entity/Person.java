package br.com.api.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "person")
@EqualsAndHashCode
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Builder.Default
    private LocalDateTime dhInclusion = LocalDateTime.now();

    @Column(nullable = false)
    private String name;

    private String phoneNumber;

    private Short age;
    private String city;
    private String state;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private Long score;
}