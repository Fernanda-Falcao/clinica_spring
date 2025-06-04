package com.br.spring_security.clinica.model.especialidade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadeService {

    private final EspecialidadeRepository repository;

    public List<Especialidade> listarTodos() {
        return repository.findAll();
    }

    public Especialidade buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
    }

    public Especialidade salvar(Especialidade especialidade) {
        if (repository.existsByNome(especialidade.getNome())) {
            throw new RuntimeException("Especialidade já cadastrada");
        }
        return repository.save(especialidade);
    }

    public Especialidade atualizar(Long id, Especialidade nova) {
        Especialidade existente = buscarPorId(id);
        existente.setNome(nova.getNome());
        return repository.save(existente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}


