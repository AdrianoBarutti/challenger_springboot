package br.com.fiap.projeto_vagamoto.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "motos_veiculos")
public class MotoVeiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    // Validations Annotations with field Bean Validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Placa é obrigatória")
    @Column(unique = true, nullable = false)
    @Pattern(
        regexp = "(^[A-Z]{3}[0-9]{4}$)|(^[A-Z]{3}[0-9][A-Z][0-9]{2}$)",
        message = "Placa deve seguir o padrão AAA0000 ou BRA1A23"
    )
    private String placa;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @NotBlank(message = "Ano é obrigatório")
    private String ano;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    // @ManyToOne
    // @JoinColumn(name = "espaco_vaga_id")
    // private VagasVeiculo espacoVaga;

    public MotoVeiculo() {
        // Construtor padrão exigido pelo JPA
    }

    public MotoVeiculo(String placa, String modelo, String cor, String marca, String ano, String tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.marca = marca;
        this.ano = ano;
        this.tipo = tipo;
    }

    // getters e setters para todos os campos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
        
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) {
       this.modelo = modelo != null ? modelo.toUpperCase() : null;
    }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getMarca() { return marca;}
    public void setMarca(String marca) {this.marca = marca;}

    public String getAno() {return ano;}
    public void setAno(String ano) {this.ano = ano;}

    public String getTipo() {return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public StatusVeiculo getStatus() {return status;}
    public void setStatus(StatusVeiculo status) {this.status = status;}

    // public VagasVeiculo getEspacoVaga() {return espacoVaga; }
    // public void setEspacoVaga(VagasVeiculo espacoVaga) { this.espacoVaga = espacoVaga; }
    
}