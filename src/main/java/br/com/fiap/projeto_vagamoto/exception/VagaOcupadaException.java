package br.com.fiap.projeto_vagamoto.exception;

public class VagaOcupadaException extends RuntimeException {
    public VagaOcupadaException(Long id) {
        super("Espaço de vaga com id " + id + " está ocupado e não pode ser alocado.");
    }
    public VagaOcupadaException(String message) {
        super(message);
    }
}
   
