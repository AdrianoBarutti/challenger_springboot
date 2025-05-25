package br.com.fiap.projeto_vagamoto.exception;

public class VagaNotFoundException extends RuntimeException {
    public VagaNotFoundException(Long id) {
        super("Espaço de vaga não encontrado com id: " + id);
    }
    public VagaNotFoundException(String message) {
        super(message);
    }
    
}
