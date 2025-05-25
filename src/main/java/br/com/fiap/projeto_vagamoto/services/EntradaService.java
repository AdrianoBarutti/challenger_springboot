package br.com.fiap.projeto_vagamoto.services;

import java.util.List;

import br.com.fiap.projeto_vagamoto.exception.VeiculoNotFoundException;
import br.com.fiap.projeto_vagamoto.exception.VeiculoIndisponivelException;

import br.com.fiap.projeto_vagamoto.model.Alocacao;
import br.com.fiap.projeto_vagamoto.model.MotoVeiculo;
import br.com.fiap.projeto_vagamoto.model.VagasVeiculo;
import br.com.fiap.projeto_vagamoto.model.StatusVeiculo;

import br.com.fiap.projeto_vagamoto.repository.MotoRepository;
import br.com.fiap.projeto_vagamoto.repository.VagaRepository;
import br.com.fiap.projeto_vagamoto.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntradaService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private ReservaRepository alocacaoRepository;

    public Alocacao realizarEntrada(String placa) {
        MotoVeiculo moto = motoRepository.findByPlaca(placa)
            .orElseThrow(() -> new VeiculoNotFoundException(placa));

        if (moto.getStatus() == StatusVeiculo.OCUPADO) {
            throw new VeiculoIndisponivelException("Moto já está ocupada.");
        }
  
        VagasVeiculo vaga = vagaRepository.findByOcupadoFalse()
            .stream()
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Nenhuma vaga disponível no momento."));


        moto.setStatus(StatusVeiculo.OCUPADO);
        vaga.setOcupado(true);

        motoRepository.save(moto);
        vagaRepository.save(vaga);

        Alocacao alocacao = new Alocacao(moto, vaga);
        alocacaoRepository.save(alocacao);

        return alocacao;
    }

    public void finalizarAlocacaoPorPlaca(String placa) {
        List<Alocacao> alocacoes = alocacaoRepository.findAllByMoto_Placa(placa);
        if (alocacoes.isEmpty()) {
            throw new RuntimeException("Alocação não encontrada para a placa: " + placa);
        }
        Alocacao alocacao = alocacoes.get(0);

        MotoVeiculo moto = alocacao.getMoto();
        VagasVeiculo vaga = alocacao.getVaga();

        moto.setStatus(StatusVeiculo.DISPONIVEL);
        vaga.setOcupado(false);

        motoRepository.save(moto);
        vagaRepository.save(vaga);

        alocacaoRepository.delete(alocacao);
    }

}
