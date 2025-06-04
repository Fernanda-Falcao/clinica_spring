package com.br.spring_security.clinica.api.especialidade;

import com.br.spring_security.clinica.model.especialidade.Especialidade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EspecialidadeRequest {
    private String nome;
    private String descricao;
    private String titulo;

    public Especialidade toEntity() {
        return Especialidade.builder()
                .nome(nome)
                .descricao(descricao)
                .titulo(titulo)
                .build();
    }
}
