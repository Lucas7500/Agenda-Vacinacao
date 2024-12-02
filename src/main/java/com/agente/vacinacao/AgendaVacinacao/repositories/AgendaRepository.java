package com.agente.vacinacao.AgendaVacinacao.repositories;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import com.agente.vacinacao.AgendaVacinacao.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {
    List<Agenda> findBySituacao(SituacaoAgenda situacao);

    List<Agenda> findByUsuario(Usuario usuario);
}
