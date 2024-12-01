package com.agente.vacinacao.AgendaVacinacao.services;

import com.agente.vacinacao.AgendaVacinacao.models.Alergia;
import com.agente.vacinacao.AgendaVacinacao.repositories.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergiaService {

    @Autowired
    private AlergiaRepository alergiaRepository;

    public Alergia salvar(Alergia alergia) {
        return alergiaRepository.save(alergia);
    }

    public List<Alergia> listarTodas() {
        return alergiaRepository.findAll();
    }

    public Optional<Alergia> buscarPorId(Integer id) {
        return alergiaRepository.findById(id);
    }

    public void excluir(Integer id) {
        alergiaRepository.deleteById(id);
    }
}