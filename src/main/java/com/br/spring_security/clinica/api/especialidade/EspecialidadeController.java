package com.br.spring_security.clinica.api.especialidade;

import com.br.spring_security.clinica.model.especialidade.Especialidade;
import com.br.spring_security.clinica.model.especialidade.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidade")
@RequiredArgsConstructor
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<List<Especialidade>> listarTodos() {
        return ResponseEntity.ok(especialidadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Especialidade> salvar(@RequestBody EspecialidadeRequest request) {
        Especialidade especialidade = request.toEntity();
        return ResponseEntity.status(201).body(especialidadeService.salvar(especialidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Long id, @RequestBody EspecialidadeRequest request) {
        Especialidade especialidade = request.toEntity();
        return ResponseEntity.ok(especialidadeService.atualizar(id, especialidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especialidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

