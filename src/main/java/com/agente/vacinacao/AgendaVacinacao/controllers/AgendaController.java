package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.enums.SituacaoAgenda;
import com.agente.vacinacao.AgendaVacinacao.models.Agenda;
import com.agente.vacinacao.AgendaVacinacao.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendas")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PutMapping("/{id}/baixa")
    public ResponseEntity<Agenda> darBaixaAgenda(
            @PathVariable Long id,
            @RequestParam SituacaoAgenda situacao
    ) {
        return ResponseEntity.ok(agendaService.darBaixaAgenda(id, situacao));
    }

    @GetMapping("/dia")
    public List<Agenda> listarAgendasDia() {
        return agendaService.listarAgendasDia();
    }

    @GetMapping("/situacao")
    public List<Agenda> listarAgendasPorSituacao(@RequestParam SituacaoAgenda situacao) {
        return agendaService.listarAgendasPorSituacao(situacao);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Agenda> listarAgendasUsuario(@PathVariable Long usuarioId) {
        return agendaService.listarAgendasUsuario(usuarioId);
    }
}