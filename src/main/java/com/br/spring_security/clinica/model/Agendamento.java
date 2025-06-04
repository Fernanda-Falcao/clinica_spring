package com.br.spring_security.clinica.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.br.spring_security.clinica.model.especialidade.Especialidade;
import com.br.spring_security.clinica.model.paciente.Paciente;

import java.time.LocalDate;

@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Inclui o ID herdado no equals/hashCode
public class Agendamento extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "id_especialidade")
	private Especialidade especialidade;

	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_horario")
	private Horario horario;

	@Column(name = "data_consulta")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Garante o formato ISO (yyyy-MM-dd)
	private LocalDate dataConsulta;
}