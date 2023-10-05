package com.blog.dto.response;

public class ResponsePaginationDTO {
    private Object items;
    private Integer totalItems;
    private Integer totalPages;
    private Integer currentPage;

    public ResponsePaginationDTO() {
    }

    public ResponsePaginationDTO(Object items, Integer totalItems, Integer totalPages, Integer currentPage) {
        this.items = items;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }

    public Object getItems() {
        return this.items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public Integer getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

}
