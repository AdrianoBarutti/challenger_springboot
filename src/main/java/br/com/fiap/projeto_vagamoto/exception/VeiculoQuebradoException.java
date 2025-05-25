package br.com.fiap.projeto_vagamoto.exception;

public class VeiculoQuebradoException extends RuntimeException {
    public VeiculoQuebradoException(Long id) {
        super("Moto com id " + id + " está quebrada e não pode ser alocada.");
    }
}