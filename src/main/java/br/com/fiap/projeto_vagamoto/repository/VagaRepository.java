package br.com.fiap.projeto_vagamoto.repository;

import java.util.Optional;
import java.util.List;

import br.com.fiap.projeto_vagamoto.model.VagasVeiculo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VagaRepository extends JpaRepository<VagasVeiculo, Long> {
    Optional<VagasVeiculo> findById(Long id);
    Page<VagasVeiculo> findAll(Pageable pageable);
    List<VagasVeiculo> findByOcupadoFalse();
    Page<VagasVeiculo> findByOcupadoFalse(Pageable pageable);
    long countByOcupadoFalse();
}