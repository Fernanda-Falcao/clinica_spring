package com.br.spring_security.clinica.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.spring_security.clinica.model.*;
import com.br.spring_security.clinica.repository.Usuario.UsuarioRepository;

// Indica que essa classe é um serviço que será gerenciado pelo Spring
@Service
public class UsuarioService implements UserDetailsService {

    // Injeta automaticamente o repositório de usuários
    @Autowired
    private UsuarioRepository repository;

    // Método para buscar um usuário pelo e-mail (sem alterar o banco)
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Implementação do método obrigatório da interface UserDetailsService
    // Usado automaticamente pelo Spring Security no processo de autenticação
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco usando o e-mail como nome de usuário
        Usuario usuario = buscarPorEmail(username);

        // Se não encontrar o usuário, lança exceção
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Cria e retorna um objeto User do Spring com e-mail, senha e roles
        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis()))
        );
    }

    // Converte a lista de perfis (roles) do usuário para uma lista de Strings
    // Exemplo: [Perfil("ROLE_ADMIN"), Perfil("ROLE_USER")] -> ["ROLE_ADMIN", "ROLE_USER"]
    private String[] getAuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getDescricao(); // getDesc deve retornar algo como "ROLE_USER"
        }
        return authorities;
    }
}