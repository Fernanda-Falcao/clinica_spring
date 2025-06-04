package com.br.spring_security.clinica.model.paciente;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public Paciente salvar(Paciente paciente) {
        if (pacienteRepository.existsByNome(paciente.getNome())) {
            throw new RuntimeException("Já existe um paciente com esse nome");
        }
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        Paciente existente = buscarPorId(id);
        BeanUtils.copyProperties(pacienteAtualizado, existente, "id", "usuario", "agendamentos");
        return pacienteRepository.save(existente);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
