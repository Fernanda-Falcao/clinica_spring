package com.br.spring_security.clinica.model.paciente;

import com.br.spring_security.clinica.model.AbstractEntity;
import com.br.spring_security.clinica.model.Agendamento;
import com.br.spring_security.clinica.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
@Builder
@NoArgsConstructor       // Construtor padrão para JPA
@AllArgsConstructor      // Construtor com todos os argumentos (opcional)
@EqualsAndHashCode(callSuper = true)
public class Paciente extends AbstractEntity {

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dtNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
