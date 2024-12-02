package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.enums.Sexo;
import com.agente.vacinacao.AgendaVacinacao.models.Alergia;
import com.agente.vacinacao.AgendaVacinacao.models.HistoricoVacinacao;
import com.agente.vacinacao.AgendaVacinacao.models.Usuario;
import com.agente.vacinacao.AgendaVacinacao.repositories.AgendaRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.AlergiaRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.HistoricoVacinacaoRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlergiaRepository alergiaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private HistoricoVacinacaoRepository historicoVacinacaoRepository;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("novoUsuario", new Usuario());
        model.addAttribute("sexos", Sexo.values());
        model.addAttribute("alergias", alergiaRepository.findAll());
        return "usuarios/lista";
    }

    @PostMapping
    public String salvarUsuario(@ModelAttribute("novoUsuario") Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/agendamentos")
    public String visualizarAgendamentos(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("agendamentos", agendaRepository.findByUsuario(usuario));
        return "usuarios/agendamentos";
    }

    @GetMapping("/{id}/historico")
    public String visualizarHistorico(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        List<HistoricoVacinacao> historico = historicoVacinacaoRepository.findByUsuario(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("historico", historico);
        return "usuarios/historico";
    }
}