package com.corpcazorla.proyecto.infrastructure.adapter.in.dto;

public class Pagination {

    public int page;
    public int size;
    public long totalElements;
    public int totalPages;

    public Pagination(int page, int size, long totalElements, int totalPages) {
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
