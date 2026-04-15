package com.petshop.controller;

import com.petshop.model.Pet;
import com.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pets")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping
    public List<Pet> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public List<Pet> buscarPorNome(@RequestParam String nome) {
        return repository.findByNomePetContaining(nome);
    }

    @PostMapping
    public Pet salvar(@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizar(@PathVariable Long id, @RequestBody Pet pet) {
        return repository.findById(id)
                .map(p -> {
                    p.setNomePet(pet.getNomePet());
                    p.setRaca(pet.getRaca());
                    p.setIdade(pet.getIdade());
                    p.setServico(pet.getServico());
                    Pet atualizado = repository.save(p);
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
