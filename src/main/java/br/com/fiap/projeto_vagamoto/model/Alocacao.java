package br.com.fiap.projeto_vagamoto.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alocacao {
    @EmbeddedId
    private AlocacaoChaveComposta id;

    @ManyToOne
    @MapsId("motoId")
    private MotoVeiculo moto;

    @ManyToOne
    @MapsId("vagaId")
    private VagasVeiculo vaga;

    private LocalDateTime dataReserva;

    // getters e setters
    public AlocacaoChaveComposta getId() {return id;}
    public void setId(AlocacaoChaveComposta id) {this.id = id;}

    public LocalDateTime getDataReserva() { return dataReserva; }
    public void setDataReserva(LocalDateTime dataReserva) { this.dataReserva = dataReserva; }

    public MotoVeiculo getMoto() { return moto; }
    public void setMoto(MotoVeiculo moto) { this.moto = moto; }

    public VagasVeiculo getVaga() { return vaga; }
    public void setVaga(VagasVeiculo vaga) { this.vaga = vaga; }

    public Alocacao() {}

    public Alocacao(MotoVeiculo moto, VagasVeiculo vaga) {
        this.id = new AlocacaoChaveComposta(moto.getId(), vaga.getId());
        this.moto = moto;
        this.vaga = vaga;
        this.dataReserva = LocalDateTime.now();
    }
}