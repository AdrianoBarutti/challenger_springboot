package br.com.fiap.projeto_vagamoto.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class AlocacaoChaveComposta implements Serializable {
    private Long motoId;
    private Long vagaId;

    public AlocacaoChaveComposta() {}

    public AlocacaoChaveComposta(Long motoId, Long vagaId) {
        this.motoId = motoId;
        this.vagaId = vagaId;
    }

    // getters e setters
    public Long getMotoId() { return motoId; }
    public void setMotoId(Long motoId) { this.motoId = motoId; }

    public Long getVagaId() { return vagaId; }
    public void setVagaId(Long vagaId) { this.vagaId = vagaId; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlocacaoChaveComposta that = (AlocacaoChaveComposta) o;
        return Objects.equals(motoId, that.motoId) && Objects.equals(vagaId, that.vagaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motoId, vagaId);
    }
    
}

