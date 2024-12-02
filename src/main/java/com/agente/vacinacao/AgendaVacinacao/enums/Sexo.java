package com.agente.vacinacao.AgendaVacinacao.enums;

import lombok.Getter;

@Getter
public enum Sexo {
    M('M'),
    F('F');

    private final char descricao;

    Sexo(char descricao) {
        this.descricao = descricao;
    }

}