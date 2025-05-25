package br.com.fiap.projeto_vagamoto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "vagas_veiculos")
public class VagasVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoVaga tipo;

    @NotNull
    private Boolean ocupado;

    public VagasVeiculo() {}

    public VagasVeiculo(String nome, String localizacao, String tipo, Boolean ocupado) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.tipo = TipoVaga.valueOf(tipo.toUpperCase());
        this.ocupado = ocupado;
    }

    // getters e setters para todos os campos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public TipoVaga getTipo() { return tipo; }
    public void setTipo(TipoVaga tipo) { this.tipo = tipo; }

    public Boolean getOcupado() { return ocupado; }
    public void setOcupado(Boolean ocupado) { this.ocupado = ocupado; }
}