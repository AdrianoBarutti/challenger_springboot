package br.com.fiap.projeto_vagamoto.repository;

import java.util.List;
import java.util.Optional;

import br.com.fiap.projeto_vagamoto.model.Alocacao;
import br.com.fiap.projeto_vagamoto.model.AlocacaoChaveComposta;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservaRepository extends JpaRepository<Alocacao, AlocacaoChaveComposta> {
    // Busca todas as alocações por ID da moto
    List<Alocacao> findAllByMoto_Id(Long motoId);

    // Busca uma única alocação por ID da moto
    Optional<Alocacao> findByMoto_Id(Long motoId);

    // Busca todas as alocações por placa da moto
    List<Alocacao> findAllByMoto_Placa(String placa);

    // Busca uma única alocação por placa da moto
    Optional<Alocacao> findByMoto_Placa(String placa);

    // Busca a alocação com data de reserva mais recente
    Optional<Alocacao> findFirstByMoto_PlacaOrderByDataReservaDesc(String placa);
}