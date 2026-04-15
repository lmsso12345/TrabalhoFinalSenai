package com.petshop.model;

import jakarta.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomePet;
    private String raca;
    private Integer idade;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Pet() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomePet() { return nomePet; }
    public void setNomePet(String nomePet) { this.nomePet = nomePet; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
}
