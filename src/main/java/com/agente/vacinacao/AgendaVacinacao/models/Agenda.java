package com.agente.vacinacao.AgendaVacinacao.models;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "agendas")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Vacina vacina;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoAgenda situacao = SituacaoAgenda.AGENDADO;

    @Column
    private LocalDate dataSituacao;

    @Column
    private Integer dosePrevista;


}