package com.petshop.controller;

import com.petshop.model.Servico;
import com.petshop.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @GetMapping
    public List<Servico> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public List<Servico> buscarPorNome(@RequestParam String nome) {
        return repository.findByNomeServicoContaining(nome);
    }

    @PostMapping
    public Servico salvar(@RequestBody Servico servico) {
        return repository.save(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        return repository.findById(id)
                .map(s -> {
                    s.setNomeServico(servico.getNomeServico());
                    s.setValor(servico.getValor());
                    s.setVeterinario(servico.getVeterinario());
                    Servico atualizado = repository.save(s);
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
