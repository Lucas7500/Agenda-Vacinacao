package com.agente.vacinacao.AgendaVacinacao.services;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import com.agente.vacinacao.AgendaVacinacao.repositories.AgendaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public Agenda darBaixaAgenda(Long id, SituacaoAgenda situacao) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agenda não encontrada"));

        agenda.setSituacao(situacao);
        agenda.setDataSituacao(LocalDate.now());

        return agendaRepository.save(agenda);
    }

    public List<Agenda> listarAgendasDia() {
        return agendaRepository.findAgendasOrdenadas(LocalDate.now());
    }

    public List<Agenda> listarAgendasPorSituacao(SituacaoAgenda situacao) {
        return agendaRepository.findBySituacao(situacao);
    }

    public List<Agenda> listarAgendasUsuario(Long usuarioId) {
        return agendaRepository.findByUsuarioId(usuarioId);
    }
}