package com.br.spring_security.clinica.model;

import com.br.spring_security.clinica.model.especialidade.Especialidade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Considera o ID herdado no equals/hashCode
public class Medico extends AbstractEntity {

	@Column(name = "nome", unique = true, nullable = false)
	private String nome; // Nome do médico (único e obrigatório)

	@Column(name = "crm", unique = true, nullable = false)
	private Integer crm; // Registro profissional (único e obrigatório)

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "data_inscricao", nullable = false)
	private LocalDate dtInscricao; // Data de inscrição no conselho

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
			name = "medicos_tem_especialidades",
			joinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "id_especialidade", referencedColumnName = "id")
	)
	private Set<Especialidade> especialidades; // Especialidades que o médico possui

	@JsonIgnore
	@OneToMany(mappedBy = "medico")
	private List<Agendamento> agendamentos; // Agendamentos associados ao médico

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario; // Usuário vinculado ao médico

	public Medico(Long id) {
		super.setId(id); // Construtor rápido com ID
	}

	public Medico(Usuario usuario) {
		this.usuario = usuario; // Construtor com usuário vinculado
	}
}