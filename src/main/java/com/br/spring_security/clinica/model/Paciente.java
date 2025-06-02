package com.br.spring_security.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Considera o ID herdado no equals/hashCode
public class Paciente extends AbstractEntity {

	@Column(name = "nome", unique = true, nullable = false)
	private String nome; // Nome do paciente (único e obrigatório)

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dtNascimento; // Data de nascimento no formato ISO

	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Agendamento> agendamentos; // Agendamentos associados ao paciente

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario; // Usuário vinculado ao paciente
}