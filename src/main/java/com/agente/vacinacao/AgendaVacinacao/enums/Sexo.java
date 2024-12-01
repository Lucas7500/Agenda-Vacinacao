package com.agente.vacinacao.AgendaVacinacao.enums;

public enum Sexo {
    M('M'),
    F('F');

    private final char valor;

    Sexo(char valor) {
        this.valor = valor;
    }

    public char getValor() {
        return valor;
    }

    public static Sexo fromChar(char valor) {
        for (Sexo sexo : values()) {
            if (sexo.valor == valor) {
                return sexo;
            }
        }

        throw new IllegalArgumentException("Sexo inválido");
    }
}
