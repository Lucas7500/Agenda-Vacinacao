package com.agente.vacinacao.AgendaVacinacao.controllers;

import com.agente.vacinacao.AgendaVacinacao.models.Alergia;
import com.agente.vacinacao.AgendaVacinacao.models.Usuario;
import com.agente.vacinacao.AgendaVacinacao.repositories.AlergiaRepository;
import com.agente.vacinacao.AgendaVacinacao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlergiaRepository alergiaRepository;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        // Validar e persistir alergias
        Set<Alergia> alergiasPersistidas = usuario
                .getAlergias()
                .stream()
                .map(alergia -> alergiaRepository.findByNome(alergia.getNome()).orElseGet(() -> alergiaRepository.save(alergia)))
                .collect(Collectors.toSet());

        usuario.setAlergias(alergiasPersistidas);
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
