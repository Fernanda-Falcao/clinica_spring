package com.br.spring_security.clinica.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "especialidades", indexes = {
		@Index(name = "idx_especialidade_titulo", columnList = "titulo")
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Considera o ID herdado no equals/hashCode
public class Especialidade extends AbstractEntity {

	@Column(name = "titulo", unique = true, nullable = false)
	private String titulo; // Nome da especialidade

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao; // Descrição detalhada (campo longo)

	@ManyToMany
	@JoinTable(
			name = "medicos_tem_especialidades",
			joinColumns = @JoinColumn(name = "id_especialidade", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id")
	)
	private List<Medico> medicos; // Médicos que possuem essa especialidade
}