package br.com.fiap.projeto_vagamoto.services;

import java.util.List;

import br.com.fiap.projeto_vagamoto.exception.VagaOcupadaException;
import br.com.fiap.projeto_vagamoto.exception.VeiculoNotFoundException;
import br.com.fiap.projeto_vagamoto.model.Alocacao;
import br.com.fiap.projeto_vagamoto.model.AlocacaoChaveComposta;
import br.com.fiap.projeto_vagamoto.model.MotoVeiculo;
import br.com.fiap.projeto_vagamoto.model.VagasVeiculo;
import br.com.fiap.projeto_vagamoto.repository.MotoRepository;
import br.com.fiap.projeto_vagamoto.repository.ReservaRepository;
import br.com.fiap.projeto_vagamoto.repository.VagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private ReservaRepository alocacaoRepository;

    public Alocacao reservarVaga(String placa) {
        // Busca todas as vagas livres
        List<VagasVeiculo> vagasLivres = vagaRepository.findByOcupadoFalse();

        if (vagasLivres.isEmpty()) {
            throw new VagaOcupadaException("Nenhuma vaga disponível no momento.");
        }

        // Busca a moto pela placa
        MotoVeiculo moto = motoRepository.findByPlaca(placa)
            .orElseThrow(() -> new VeiculoNotFoundException("Moto com placa " + placa + " não encontrada."));

        // Verifica se já existe uma reserva para essa moto
        boolean reservaExistente = alocacaoRepository.existsById(new AlocacaoChaveComposta(moto.getId(), null));
        if (reservaExistente) {
            throw new RuntimeException("Já existe uma reserva para a moto com placa " + placa + ".");
        }

        // Seleciona a primeira vaga livre
        VagasVeiculo vaga = vagasLivres.get(0);

        // Cria a alocação
        Alocacao alocacao = new Alocacao(moto, vaga);

        // Salva a alocação e atualiza a vaga
        vaga.setOcupado(true);
        alocacaoRepository.save(alocacao);
        vagaRepository.save(vaga);

        return alocacao;
    }
}