package com.agente.vacinacao.AgendaVacinacao.repositories;

import com.agente.vacinacao.AgendaVacinacao.models.HistoricoVacinacao;
import com.agente.vacinacao.AgendaVacinacao.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoVacinacaoRepository extends JpaRepository<HistoricoVacinacao, Integer> {
    List<HistoricoVacinacao> findByUsuario(Usuario usuario);
}