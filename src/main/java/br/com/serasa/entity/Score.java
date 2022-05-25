package br.com.serasa.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "score")
@EqualsAndHashCode
public class Score {
    @Id
    @Column(unique = true, nullable = false)
    private String scoreDescription;

    @Column(nullable = false)
    private Long min;

    @Column(nullable = false)
    private Long max;
}
