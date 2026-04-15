package com.petshop.model;

import jakarta.persistence.*;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeServico;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    public Servico() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeServico() { return nomeServico; }
    public void setNomeServico(String nomeServico) { this.nomeServico = nomeServico; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
}
