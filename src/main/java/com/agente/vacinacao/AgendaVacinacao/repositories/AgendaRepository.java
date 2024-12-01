package com.agente.vacinacao.AgendaVacinacao.repositories;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findBySituacao(SituacaoAgenda situacao);

    @Query("SELECT a FROM Agenda a WHERE a.data = :dataAgenda " +
            "ORDER BY CASE " +
            "   WHEN a.situacao = 'AGENDADA' THEN 1 " +
            "   WHEN a.situacao = 'REALIZADA' THEN 2 " +
            "   WHEN a.situacao = 'CANCELADA' THEN 3 " +
            "   ELSE 4 END")
    List<Agenda> findAgendasPorDataOrdenadasPorSituacao(@Param("dataAgenda") LocalDate dataAgenda);
}
