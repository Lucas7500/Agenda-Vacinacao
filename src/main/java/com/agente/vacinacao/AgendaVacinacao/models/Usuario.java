package com.agente.vacinacao.AgendaVacinacao.models;

import com.agente.vacinacao.AgendaVacinacao.enums.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Sexo sexo;

    @Column(length = 60, nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private int numero;

    @Column(length = 40, nullable = false)
    private String setor;

    @Column(length = 40, nullable = false)
    private String cidade;

    @Column(length = 2, nullable = false)
    private String uf;

    @ManyToMany
    @JoinTable(
            name = "usuario_alergias",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "alergia_id")
    )
    private Set<Alergia> alergias = new HashSet<>();
}
