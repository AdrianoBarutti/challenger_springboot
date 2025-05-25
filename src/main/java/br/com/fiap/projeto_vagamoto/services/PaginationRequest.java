package br.com.fiap.projeto_vagamoto.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationRequest {
    private int page;
    private int size;
    private Sort.Direction direction;
    private String sortBy;

    public PaginationRequest(int page, int size, Sort.Direction direction, String sortBy) {
        this.page = page;
        this.size = size;
        this.direction = direction;
        this.sortBy = sortBy;
    }

    // Getters (opcionais, dependendo do uso)
    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public String getSortBy() {
        return sortBy;
    }

    /**
     * Converte este objeto PaginationRequest em um objeto Pageable do Spring Data.
     * @return um objeto Pageable configurado.
     */
    public Pageable toPageable() {
        return PageRequest.of(page, size, direction, sortBy);
    }
}