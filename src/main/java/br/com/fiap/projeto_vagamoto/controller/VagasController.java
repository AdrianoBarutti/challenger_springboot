package br.com.fiap.projeto_vagamoto.controller;

import br.com.fiap.projeto_vagamoto.services.PaginationRequest;
import br.com.fiap.projeto_vagamoto.model.VagasVeiculo;

import br.com.fiap.projeto_vagamoto.services.VagaService;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;



import java.util.List;



@RestController
@RequestMapping("/vagas")
public class VagasController {

    @Autowired
    private VagaService vagaService; 


    @GetMapping("/listar")
    @ResponseBody
    public ResponseEntity<?> listar(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        List<VagasVeiculo> vagas = vagaService.listarTodas(pageable);
        long total = vagaService.count();
        PageImpl<VagasVeiculo> vagasPage = new PageImpl<>(vagas, pageable, total);
        return ResponseEntity.ok(vagasPage);
    }

    @GetMapping(value = "/listar-vagas-disponiveis", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> listarVagasDisponiveis(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer size,
        @RequestParam(defaultValue = "ASC") Sort.Direction direction,
        @RequestParam(defaultValue = "id") String sortBy
    ) {
        if (size < 1) {
            Map<String, String> errorResponse = Map.of(
                "error", "Parâmetro inválido",
                "message", "O tamanho da página deve ser maior que zero."
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        final PaginationRequest requestDto = new PaginationRequest(page, size, direction, sortBy);
        Pageable pageable = requestDto.toPageable();
        List<VagasVeiculo> vagas = vagaService.listarDisponiveis(pageable);
        long total = vagaService.countDisponiveis();
        Page<VagasVeiculo> vagasPage = new PageImpl<>(vagas, pageable, total);
        return ResponseEntity.ok(vagasPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasVeiculo> buscarPorId(@PathVariable Long id) {
        VagasVeiculo vaga = vagaService.buscarPorId(id);
        return ResponseEntity.ok(vaga);
    }

    @PostMapping(value = "/criar", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public VagasVeiculo criar(@RequestBody @Valid VagasVeiculo espaco) {
        return vagaService.criar(espaco); // Use o serviço
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagasVeiculo> atualizar(@PathVariable Long id, @RequestBody @Valid VagasVeiculo espaco) {
        VagasVeiculo vagaAtualizada = vagaService.atualizar(id, espaco); // Use o serviço
        return ResponseEntity.ok(vagaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vagaService.deletar(id); // Use o serviço
        return ResponseEntity.noContent().build();
    }
}