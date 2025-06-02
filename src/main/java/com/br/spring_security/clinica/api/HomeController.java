package com.br.spring_security.clinica.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController // (retorna JSON)
public class HomeController {

    // Endpoint para "/" e "/home"
    @GetMapping({"/", "/home"})
    public ResponseEntity<Map<String, String>> home() {
        // Cria um JSON simples com uma mensagem
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bem-vindo à home!");
        return ResponseEntity.ok(response); // HTTP 200
    }

    // Endpoint para "/login"
    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bem-vindo à página de login!");
        return ResponseEntity.ok(response); // HTTP 200
    }

    // Endpoint para "/login-error"
    @GetMapping("/login-error")
    public ResponseEntity<Map<String, String>> loginError() {
        Map<String, String> response = new HashMap<>();
        response.put("alerta", "erro");
        response.put("titulo", "Credenciais inválidas");
        response.put("texto", "Login ou senha incorretos, tente novamente");
        response.put("subtexto", "Acesso permitido apenas para cadastros ativados");

        return ResponseEntity.status(401).body(response); // HTTP 401 Unauthorized
    }
}