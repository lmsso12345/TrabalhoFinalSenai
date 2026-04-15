package com.petshop.repository;

import com.petshop.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    List<Veterinario> findByNomeContaining(String nome);
}
