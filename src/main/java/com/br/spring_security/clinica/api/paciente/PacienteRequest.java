package com.br.spring_security.clinica.api.paciente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.br.spring_security.clinica.model.Usuario;
import com.br.spring_security.clinica.model.paciente.Paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequest {

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtNascimento;

    private Long idUsuario;

    public Paciente build() {

        // Cria apenas o objeto com ID para o relacionamento
        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);

        return Paciente.builder()
                .nome(nome)
                .dtNascimento(dtNascimento)
                .usuario(usuario)
                .build();
    }
}
