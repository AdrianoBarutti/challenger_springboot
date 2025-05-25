package br.com.fiap.projeto_vagamoto.exception;

public class VeiculoNotFoundException extends RuntimeException {
    public VeiculoNotFoundException(Long id) {
        super("Veículo com id " + id + " não encontrado.");
    }

    public VeiculoNotFoundException(String message) {
        super(message);
    }
}