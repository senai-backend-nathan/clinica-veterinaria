package br.com.senai.clinica.entity;



import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {
    private Long id;
    @Size(min = 8, max = 8, message = "Um CEP deve conter 8 caracteres")
    private String cep;
    @NotBlank @Size(min= 10, max = 100)
    private String logradouro;
    @Size(min = 2, max = 2)
    private String uf;
    @NotBlank
    private String bairro;
    @Size (min = 2, max = 6)
    private String numero;
    @Size (max = 100)
    private String complemento;
    @Size (max = 100)
    private String referencia;
    @NotBlank
    private Boolean principal;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public Boolean getPrincipal() {
        return principal;
    }
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    

    
}
