package br.com.fiap.projeto_vagamoto.services; 

import br.com.fiap.projeto_vagamoto.model.VagasVeiculo;
import br.com.fiap.projeto_vagamoto.repository.VagaRepository;
import br.com.fiap.projeto_vagamoto.exception.VagaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Cacheable(value = "vagas", key = "'listarTodas-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public List<VagasVeiculo> listarTodas(Pageable pageable) {
        return vagaRepository.findAll(pageable).getContent();
    }

    @Cacheable(value = "vagas", key = "'listarDisponiveis-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public List<VagasVeiculo> listarDisponiveis(Pageable pageable) {
        return vagaRepository.findByOcupadoFalse(pageable).getContent();
    }

    public long countDisponiveis() {
        return vagaRepository.countByOcupadoFalse();
}

    @Cacheable(value = "vagas", key = "'buscarPorId-' + #id")
    public VagasVeiculo buscarPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new VagaNotFoundException(id));
    }

    @CacheEvict(value = "vagas", allEntries = true)
    public VagasVeiculo criar(VagasVeiculo vaga) {
        return vagaRepository.save(vaga);
    }

    @CachePut(value = "vagas", key = "'buscarPorId-' + #id") 
    @CacheEvict(value="vagas", allEntries = true)
    public VagasVeiculo atualizar(Long id, VagasVeiculo vagaAtualizada) {
        VagasVeiculo existente = vagaRepository.findById(id)
                .orElseThrow(() -> new VagaNotFoundException(id));
        existente.setNome(vagaAtualizada.getNome());
        existente.setLocalizacao(vagaAtualizada.getLocalizacao());
        existente.setTipo(vagaAtualizada.getTipo());
        existente.setOcupado(vagaAtualizada.getOcupado());
        return vagaRepository.save(existente);
    }

    @CacheEvict(value = "vagas", allEntries = true) 
    public void deletar(Long id) {
        if (!vagaRepository.existsById(id)) {
            throw new VagaNotFoundException(id);
        }
        vagaRepository.deleteById(id);
    }

    public long count() {
        return vagaRepository.count();
}

}   