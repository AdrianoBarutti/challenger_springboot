package br.com.fiap.projeto_vagamoto.exception;

public class VeiculoIndisponivelException extends RuntimeException {
    public VeiculoIndisponivelException(Long id) {
        super("Veículo não disponível com id: " + id);
    }
    public VeiculoIndisponivelException(String placa) {
        super("Veículo não disponível com placa: " + placa);
    }
}
