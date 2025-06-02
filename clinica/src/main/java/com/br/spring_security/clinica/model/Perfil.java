package com.br.spring_security.clinica.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Considera o ID herdado no equals/hashCode
public class Perfil extends AbstractEntity {

	@Column(name = "descricao", nullable = false, unique = true)
	private String desc; // Nome do perfil (ex: ADMIN, MEDICO, PACIENTE)

	public Perfil(Long id) {
		super.setId(id); // Construtor rápido com ID
	}
}