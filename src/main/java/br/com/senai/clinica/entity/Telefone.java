package br.com.senai.clinica.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class Telefone {
    private Long id; 
    @Size (min = 10, max = 15)
    private String  numero;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
   
}
