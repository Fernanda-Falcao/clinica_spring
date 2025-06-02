package com.br.spring_security.clinica.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "horas", indexes = {
		@Index(name = "idx_hora_minuto", columnList = "hora_minuto") // Índice para melhorar performance em buscas por horário
})
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Considera o ID herdado no equals/hashCode
public class Horario extends AbstractEntity {

	@Column(name = "hora_minuto", unique = true, nullable = false)
	private LocalTime horaMinuto; // Hora e minuto da consulta
}