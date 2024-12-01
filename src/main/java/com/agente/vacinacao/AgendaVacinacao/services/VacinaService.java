package com.agente.vacinacao.AgendaVacinacao.services;

import com.agente.vacinacao.AgendaVacinacao.models.Vacina;
import com.agente.vacinacao.AgendaVacinacao.repositories.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    public Vacina salvar(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public List<Vacina> listarTodas() {
        return vacinaRepository.findAll();
    }

    public Optional<Vacina> buscarPorId(Integer id) {
        return vacinaRepository.findById(id);
    }

    public void excluir(Integer id) {
        vacinaRepository.deleteById(id);
    }
}