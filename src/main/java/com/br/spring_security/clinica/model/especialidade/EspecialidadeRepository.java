package com.br.spring_security.clinica.model.especialidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
    boolean existsByNome(String nome);
}

