package com.petshop.controller;

import com.petshop.model.Veterinario;
import com.petshop.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "*")
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository repository;

    @GetMapping
    public List<Veterinario> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public List<Veterinario> buscarPorNome(@RequestParam String nome) {
        return repository.findByNomeContaining(nome);
    }

    @PostMapping
    public Veterinario salvar(@RequestBody Veterinario veterinario) {
        return repository.save(veterinario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> atualizar(@PathVariable Long id, @RequestBody Veterinario veterinario) {
        return repository.findById(id)
                .map(v -> {
                    v.setNome(veterinario.getNome());
                    v.setCrmv(veterinario.getCrmv());
                    v.setEspecialidade(veterinario.getEspecialidade());
                    Veterinario atualizado = repository.save(v);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
