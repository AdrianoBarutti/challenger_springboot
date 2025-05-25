package br.com.fiap.projeto_vagamoto.services;

import br.com.fiap.projeto_vagamoto.model.VagasVeiculo;
import br.com.fiap.projeto_vagamoto.exception.VagaNotFoundException;
import br.com.fiap.projeto_vagamoto.exception.VeiculoNotFoundException;
import br.com.fiap.projeto_vagamoto.model.MotoVeiculo;
import br.com.fiap.projeto_vagamoto.repository.MotoRepository;

import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public long count() {
        return motoRepository.count();
    }

    @Cacheable(value = "motos", key = "'listarTodas-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()")
    public List<MotoVeiculo> listarTodas(Pageable pageable) {
        return motoRepository.findAll(pageable).getContent();
    }

    @Cacheable(value = "motos", key = "'buscarPorId-' + #id")
    public MotoVeiculo buscarPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException(id));
    }
    @Cacheable(value = "motos", key = "'buscarPorModelo-' + #modelo")
    public List<MotoVeiculo> buscarPorModelo(String modelo) {
        return motoRepository.findByModeloContainingIgnoreCase(modelo);
    }
    @Cacheable(value = "motos", key = "'buscarPorPlaca-' + #placa")
    public MotoVeiculo buscarPorPlaca(String placa) {
        return motoRepository.findByPlaca(placa)
                .orElseThrow(() -> new VeiculoNotFoundException(placa));
    }

    @CacheEvict(value = "motos", allEntries = true)
    public MotoVeiculo criar(MotoVeiculo moto) {
        return motoRepository.save(moto);
    }

    @CachePut(value = "motos", key = "'buscarPorId-' + #id")
    @CacheEvict(value = "motos", allEntries = true)
    public MotoVeiculo atualizar(Long id, MotoVeiculo moto) {
        MotoVeiculo existente = motoRepository.findById(id)
            .orElseThrow(() -> new VeiculoNotFoundException(id));
        existente.setCor(moto.getCor());
        existente.setMarca(moto.getMarca());
        existente.setAno(moto.getAno());
        existente.setTipo(moto.getTipo());
        existente.setStatus(moto.getStatus());
        return motoRepository.save(existente);
    }

    @CacheEvict(value = "motos", allEntries = true)
    public void deletar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new VeiculoNotFoundException(id);
        }
        motoRepository.deleteById(id);
    }
    

    
    
}
