package com.agente.vacinacao.AgendaVacinacao.repositories;

import com.agente.vacinacao.AgendaVacinacao.models.Alergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlergiaRepository extends JpaRepository<Alergia, Long> {
    Optional<Alergia> findByNome(String nome);
}