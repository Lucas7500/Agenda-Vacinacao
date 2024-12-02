package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import com.agente.vacinacao.AgendaVacinacao.models.HistoricoVacinacao;
import com.agente.vacinacao.AgendaVacinacao.models.Vacina;
import com.agente.vacinacao.AgendaVacinacao.repositories.AgendaRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.HistoricoVacinacaoRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.UsuarioRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private HistoricoVacinacaoRepository historicoVacinacaoRepository;

    @GetMapping
    public String listarAgendas(
            @RequestParam(value = "situacao", required = false) SituacaoAgenda situacao,
            Model model
    ) {
        List<Agenda> agendas;

        if (situacao != null) {
            agendas = agendaRepository.findBySituacao(situacao);
        } else {
            agendas = agendaRepository.findAll();
        }

        model.addAttribute("agendas", agendas);

        Agenda novaAgenda = new Agenda();
        novaAgenda.setDataSituacao(LocalDate.now());
        novaAgenda.setData(LocalDate.now());
        novaAgenda.setHora(LocalTime.of(8, 0));
        novaAgenda.setSituacao(SituacaoAgenda.AGENDADO);

        model.addAttribute("novaAgenda", novaAgenda);
        model.addAttribute("situacoes", SituacaoAgenda.values());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("vacinas", vacinaRepository.findAll());

        return "agendas/lista";
    }

    @PostMapping
    public String salvarAgenda(@ModelAttribute("novaAgenda") Agenda agendaOriginal) {
        Vacina vacina = agendaOriginal.getVacina();

        List<Agenda> agendasParaSalvar = new ArrayList<>();

        for (int dose = 1; dose <= vacina.getDoses(); dose++) {
            Agenda novaAgenda = new Agenda();
            novaAgenda.setUsuario(agendaOriginal.getUsuario());
            novaAgenda.setVacina(vacina);
            novaAgenda.setSituacao(agendaOriginal.getSituacao());
            novaAgenda.setHora(agendaOriginal.getHora());
            novaAgenda.setObservacoes("Dose " + dose + " de " + vacina.getDoses());

            if (novaAgenda.getSituacao() == SituacaoAgenda.AGENDADO) {
                novaAgenda.setDataSituacao(null);
            } else {
                novaAgenda.setDataSituacao(LocalDate.now());
            }

            if (dose == 1) {
                novaAgenda.setData(agendaOriginal.getData());
            } else {
                LocalDate dataUltimaDose = agendasParaSalvar.get(agendasParaSalvar.size() - 1).getData();
                switch (vacina.getPeriodicidade()) {
                    case ANOS:
                        novaAgenda.setData(dataUltimaDose.plusYears(vacina.getIntervalo()));
                        break;
                    case MESES:
                        novaAgenda.setData(dataUltimaDose.plusMonths(vacina.getIntervalo()));
                        break;
                    case SEMANAS:
                        novaAgenda.setData(dataUltimaDose.plusWeeks(vacina.getIntervalo()));
                        break;
                    case DIAS:
                        novaAgenda.setData(dataUltimaDose.plusDays(vacina.getIntervalo()));
                        break;
                }
            }

            agendasParaSalvar.add(novaAgenda);
        }

        agendaRepository.saveAll(agendasParaSalvar);

        return "redirect:/agendas";
    }


    @PostMapping("/dar-baixa")
    public String darBaixa(
            @RequestParam("id") Integer id,
            @RequestParam("situacao") SituacaoAgenda situacao
    ) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));

        if (situacao == SituacaoAgenda.REALIZADO) {
            agenda.setSituacao(situacao);
            agenda.setDataSituacao(LocalDate.now());
            agendaRepository.save(agenda);

            HistoricoVacinacao historico = new HistoricoVacinacao();
            historico.setUsuario(agenda.getUsuario());
            historico.setVacina(agenda.getVacina());
            historico.setDataAplicacao(agenda.getData());
            historico.setObservacoes(agenda.getObservacoes());
            historicoVacinacaoRepository.save(historico);
        } else if (situacao == SituacaoAgenda.CANCELADO) {
            agenda.setSituacao(situacao);
            agenda.setDataSituacao(LocalDate.now());
            agendaRepository.save(agenda);
        }

        return "redirect:/agendas";
    }

    @PostMapping("/remover")
    public String removerAgenda(@ModelAttribute("id") Integer id) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agenda não encontrada"));
        agendaRepository.delete(agenda);
        return "redirect:/agendas";
    }


}