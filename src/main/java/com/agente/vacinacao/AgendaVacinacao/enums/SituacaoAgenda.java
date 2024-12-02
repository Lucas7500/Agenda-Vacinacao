package com.agente.vacinacao.AgendaVacinacao.enums;

import lombok.Getter;

@Getter
public enum SituacaoAgenda {
    AGENDADO("AGENDADO"),
    CANCELADO("CANCELADO"),
    REALIZADO("REALIZADO");

    private final String descricao;

    SituacaoAgenda(String descricao) {
        this.descricao = descricao;
    }

}