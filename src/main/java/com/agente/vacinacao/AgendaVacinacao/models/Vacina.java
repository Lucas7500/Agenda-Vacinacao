package com.agente.vacinacao.AgendaVacinacao.models;

import com.agente.vacinacao.AgendaVacinacao.enums.Periodicidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vacinas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String titulo;

    @Column(length = 200, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int doses;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Periodicidade periodicidade;

    @Column(nullable = false)
    private int intervalo;
}
