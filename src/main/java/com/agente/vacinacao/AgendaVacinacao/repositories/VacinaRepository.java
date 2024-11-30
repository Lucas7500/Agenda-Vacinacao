package com.agente.vacinacao.AgendaVacinacao.repositories;

import com.agente.vacinacao.AgendaVacinacao.models.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    Optional<Vacina> findByNome(String nome);
}