package br.com.fiap.projeto_vagamoto.repository;

import java.util.List;
import java.util.Optional;

import br.com.fiap.projeto_vagamoto.model.MotoVeiculo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MotoRepository extends JpaRepository<MotoVeiculo, Long> {
    List<MotoVeiculo> findByModeloContainingIgnoreCase(String modelo);
    Optional<MotoVeiculo> findByPlaca(String placa);
    Page<MotoVeiculo> findAll(Pageable pageable);
}