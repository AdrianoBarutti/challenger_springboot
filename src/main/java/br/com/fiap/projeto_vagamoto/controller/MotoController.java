package br.com.fiap.projeto_vagamoto.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.fiap.projeto_vagamoto.services.MotoService;
import br.com.fiap.projeto_vagamoto.model.MotoVeiculo;



@RestController
@RequestMapping("/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping(value = "/listar-motos", produces = "application/json")
    public ResponseEntity<?> listar(
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
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        List<MotoVeiculo> motos = motoService.listarTodas(pageable); 
        long total = motoService.count(); 
        PageImpl<MotoVeiculo> motosPage = new PageImpl<>(motos, pageable, total);
        return ResponseEntity.ok(motosPage);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MotoVeiculo buscarPorId(@PathVariable Long id) {
        return motoService.buscarPorId(id);
    }

    @GetMapping("/buscar-por-modelo")
    public List<MotoVeiculo> buscarPorModelo(@RequestParam String modelo) {
        return motoService.buscarPorModelo(modelo);
    }

    @GetMapping("/buscar-por-placa")
    public MotoVeiculo buscarPorPlaca(@RequestParam @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}$") String placa) {
        return motoService.buscarPorPlaca(placa);
    }

    @PostMapping("/criar-moto")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public MotoVeiculo criar(@RequestBody @Valid MotoVeiculo moto) {
        return motoService.criar(moto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MotoVeiculo atualizar(@PathVariable Long id, @RequestBody @Valid MotoVeiculo moto) {
        return motoService.atualizar(id, moto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        motoService.deletar(id);
    }
}