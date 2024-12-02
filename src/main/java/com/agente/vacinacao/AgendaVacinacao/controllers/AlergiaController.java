package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.models.Alergia;
import com.agente.vacinacao.AgendaVacinacao.repositories.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}