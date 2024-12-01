package com.agente.vacinacao.AgendaVacinacao.services;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import com.agente.vacinacao.AgendaVacinacao.models.Usuario;
import com.agente.vacinacao.AgendaVacinacao.models.Vacina;
import com.agente.vacinacao.AgendaVacinacao.repositories.AgendaRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.VacinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private VacinaRepository vacinaRepository;


    public void salvar(Agenda agenda) {
        agendaRepository.save(agenda);
    }

    public List<Agenda> listarTodas() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Integer id) {
        return agendaRepository.findById(id);
    }

    public void excluir(Integer id) {
        agendaRepository.deleteById(id);
    }

    @Transactional
    public List<Agenda> criarAgendasParaVacina(Vacina vacina, Usuario usuario, LocalDate dataInicial) {
        List<Agenda> agendas = new ArrayList<>();
        LocalDate dataAgenda = dataInicial;

        for (int dose = 1; dose <= vacina.getDoses(); dose++) {
            Agenda agenda = new Agenda();
            agenda.setVacina(vacina);
            agenda.setUsuario(usuario);
            agenda.setData(dataAgenda);
            agenda.setSituacao(SituacaoAgenda.AGENDADO);
            agenda.setObservacoes("Dose " + dose + " de " + vacina.getDoses());

            agendaRepository.save(agenda);
            agendas.add(agenda);

            dataAgenda = calcularProximaData(dataAgenda, vacina);
        }

        return agendas;
    }

    private LocalDate calcularProximaData(LocalDate dataAtual, Vacina vacina) {
        return switch (vacina.getPeriodicidade()) {
            case DIAS -> dataAtual.plusDays(vacina.getIntervalo());
            case SEMANAS -> dataAtual.plusWeeks(vacina.getIntervalo());
            case MESES -> dataAtual.plusMonths(vacina.getIntervalo());
            case ANOS -> dataAtual.plusYears(vacina.getIntervalo());
            default -> dataAtual;
        };
    }

    public void darBaixaAgenda(Integer agendaId, SituacaoAgenda situacao) {
        Agenda agenda = agendaRepository.findById(agendaId)
            .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        agenda.setSituacao(situacao);
        agenda.setDataSituacao(LocalDate.now());

        agendaRepository.save(agenda);
    }
}