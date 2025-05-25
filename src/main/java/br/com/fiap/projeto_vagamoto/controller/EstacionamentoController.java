package br.com.fiap.projeto_vagamoto.controller;

import java.util.List;

import br.com.fiap.projeto_vagamoto.dto.EntradaRequest;
import br.com.fiap.projeto_vagamoto.dto.PlacaRequest;
import br.com.fiap.projeto_vagamoto.model.Alocacao;
import br.com.fiap.projeto_vagamoto.services.EntradaService;
import br.com.fiap.projeto_vagamoto.services.ReservaService;
import br.com.fiap.projeto_vagamoto.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private ReservaRepository alocacaoRepository;

    /**
     * Aloca uma moto em uma vaga.
     */
    @PostMapping("/reservar")
    @ResponseStatus(HttpStatus.CREATED)
    public Alocacao reservarVaga(@RequestBody PlacaRequest request) {
        return reservaService.reservarVaga(request.getPlaca());
    }

    @PostMapping("/entrada")
    @ResponseStatus(HttpStatus.CREATED)
    public Alocacao entradaEstacionamento(@RequestBody EntradaRequest request) {
        return entradaService.realizarEntrada(request.getPlaca());
    }

    /**
     * Busca todas as alocações de uma placa específica.
     *
     * @param placa Placa da moto
     * @return Lista de alocações da moto
     */
    @GetMapping("/moto")
    public Alocacao buscarAlocacaoMaisRecentePorPlaca(@RequestParam String placa) {
        return alocacaoRepository.findFirstByMoto_PlacaOrderByDataReservaDesc(placa)
            .orElseThrow(() -> new RuntimeException("Nenhuma alocação encontrada para a placa " + placa));
    }

    @DeleteMapping("/finalizar/{placa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizarAlocacao(@PathVariable String placa) {
        entradaService.finalizarAlocacaoPorPlaca(placa);
    }
}   