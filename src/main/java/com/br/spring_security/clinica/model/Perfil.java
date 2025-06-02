package com.br.spring_security.clinica.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor // Construtor sem argumentos (requerido por JPA)
@EqualsAndHashCode(callSuper = true) // Considera o ID da superclasse no equals/hashCode
public class Perfil extends AbstractEntity {

	// Nome único do perfil, ex: "ROLE_ADMIN"
	@Column(name = "descricao", nullable = false, unique = true)
	private String descricao;

	// Construtor com ID para uso rápido (ex: associação manual)
	public Perfil(Long id) {
		super.setId(id);
	}

	// Construtor completo (opcional, pode ajudar em testes ou seeds)
	public Perfil(Long id, String descricao) {
		super.setId(id);
		this.descricao = descricao;
	}
}