package br.com.fiap.projeto_vagamoto.dto;

import java.time.LocalDateTime;


public class EntradaRequest {
    private String placa;
    private LocalDateTime dataEntrada;

    // Getters e setters
        public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}

    public LocalDateTime getDataEntrada() {return dataEntrada;}
    public void setDataEntrada(LocalDateTime dataEntrada) {this.dataEntrada = dataEntrada;}

    public EntradaRequest(String placa, LocalDateTime dataEntrada) {
        this.placa = placa;
        this.dataEntrada = dataEntrada;
    }
}