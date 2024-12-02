package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.models.Alergia;
import com.agente.vacinacao.AgendaVacinacao.repositories.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alergias")
public class AlergiaController {

    @Autowired
    private AlergiaRepository alergiaRepository;

    @GetMapping
    public String listarAlergias(Model model) {
        model.addAttribute("alergias", alergiaRepository.findAll());
        model.addAttribute("novaAlergia", new Alergia());
        return "alergias/lista";
    }

    @PostMapping
    public String salvarAlergia(@ModelAttribute("novaAlergia") Alergia alergia) {
        alergiaRepository.save(alergia);
        return "redirect:/alergias";
    }

    @PostMapping("/remover")
    public String removerAlergia(@ModelAttribute("id") Integer id) {
        Alergia alergia = alergiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Alergia não encontrada"));
        alergiaRepository.delete(alergia);
        return "redirect:/alergias";
    }

}