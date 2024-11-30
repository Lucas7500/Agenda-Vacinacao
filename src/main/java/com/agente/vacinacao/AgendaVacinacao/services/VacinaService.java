package com.agente.vacinacao.AgendaVacinacao.services;

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

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public List<Agenda> criarAgendasParaVacina(Usuario usuario, Vacina vacina, LocalDate dataInicial) {
        List<Agenda> agendas = new ArrayList<>();

        for (int dose = 1; dose <= vacina.getDoses(); dose++) {
            Agenda agenda = new Agenda();
            agenda.setUsuario(usuario);
            agenda.setVacina(vacina);
            agenda.setDosePrevista(dose);

            // Calcula data baseado na periodicidade e dose
            LocalDate dataAgenda = dataInicial;
            if (dose > 1 && vacina.getPeriodicidade() != null && vacina.getIntervalo() != null) {
                switch (vacina.getPeriodicidade()) {
                    case 365: // Anos
                        dataAgenda = dataInicial.plusYears(vacina.getIntervalo() * (dose - 1));
                        break;
                    case 30: // Meses
                        dataAgenda = dataInicial.plusMonths(vacina.getIntervalo() * (dose - 1));
                        break;
                    case 7: // Semanas
                        dataAgenda = dataInicial.plusWeeks(vacina.getIntervalo() * (dose - 1));
                        break;
                    default: // Dias
                        dataAgenda = dataInicial.plusDays(vacina.getIntervalo() * (dose - 1));
                }
            }

            agenda.setData(dataAgenda);
            agendas.add(agenda);
        }

        return agendaRepository.saveAll(agendas);
    }
}
