package com.br.spring_security.clinica.repository.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.spring_security.clinica.model.*;

// Interface que permite realizar operações no banco de dados com a entidade Usuario
// Estende JpaRepository para herdar métodos como save(), findAll(), findById(), delete(), etc.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método customizado que busca um usuário pelo e-mail
    // O Spring Data JPA entende o nome do método e cria a consulta automaticamente
    Usuario findByEmail(String email);
}