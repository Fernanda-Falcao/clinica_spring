package com.br.spring_security.clinica.model.especialidade;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.br.spring_security.clinica.model.AbstractEntity;
import com.br.spring_security.clinica.model.Medico;

@Entity
@Table(name = "especialidades", indexes = {
        @Index(name = "idx_especialidade_nome", columnList = "nome")
})
@Data
@Builder
@NoArgsConstructor       // Construtor padrão para JPA
@AllArgsConstructor      // Construtor com todos os argumentos
@EqualsAndHashCode(callSuper = true)
public class Especialidade extends AbstractEntity {

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "medicos_tem_especialidades",
            joinColumns = @JoinColumn(name = "id_especialidade", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id")
    )
    private List<Medico> medicos;
}
