package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.enums.Periodicidade;
import com.agente.vacinacao.AgendaVacinacao.models.Vacina;
import com.agente.vacinacao.AgendaVacinacao.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private VacinaRepository vacinaRepository;

    @GetMapping
    public String listarVacinas(Model model) {
        model.addAttribute("vacinas", vacinaRepository.findAll());
        model.addAttribute("novaVacina", new Vacina());
        model.addAttribute("periodicidades", Periodicidade.values());
        return "vacinas/lista";
    }

    @PostMapping
    public String salvarVacina(@ModelAttribute("novaVacina") Vacina vacina) {
        vacinaRepository.save(vacina);
        return "redirect:/vacinas";
    }

    @PostMapping("/remover")
    public String removerVacina(@ModelAttribute("id") Integer id) {
        Vacina vacina = vacinaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Vacina não encontrada"));
        vacinaRepository.delete(vacina);
        return "redirect:/vacinas";
    }
}