package com.agente.vacinacao.AgendaVacinacao.models;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "agendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private SituacaoAgenda situacao;

    @Column(name = "data_situacao", nullable = true)
    private LocalDate dataSituacao;

    @Column(length = 200, nullable = false)
    private String observacoes;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Vacina vacina;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
}
