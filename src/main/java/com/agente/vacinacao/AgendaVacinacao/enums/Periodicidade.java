package com.agente.vacinacao.AgendaVacinacao.enums;

public enum Periodicidade {
    DIAS(1),
    SEMANAS(2),
    MESES(3),
    ANOS(4);

    private final int valor;

    Periodicidade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return this.name();
    }
}
