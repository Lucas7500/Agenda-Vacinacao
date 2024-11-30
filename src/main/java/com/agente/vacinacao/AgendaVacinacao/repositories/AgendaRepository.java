package com.agente.vacinacao.AgendaVacinacao.repositories;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    List<Agenda> findByUsuarioId(Long usuarioId);
    List<Agenda> findBySituacao(SituacaoAgenda situacao);

    @Query("SELECT a FROM Agenda a WHERE a.data = :data ORDER BY " +
            "CASE a.situacao " +
            "WHEN 'AGENDADO' THEN 1 " +
            "WHEN 'REALIZADO' THEN 2 " +
            "WHEN 'CANCELADO' THEN 3 END")
    List<Agenda> findAgendasOrdenadas(@Param("data") LocalDate data);
}