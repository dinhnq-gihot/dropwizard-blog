package com.blog.dto.response;

public class ResponsePaginationDTO {
    private Object items;
    private Integer totalItems;
    private Integer itemPerPage;
    private Integer totalPages;
    private Integer currentPage;

    public ResponsePaginationDTO() {
    }

    public ResponsePaginationDTO(Object items, Integer totalItems, Integer itemPerPage, Integer totalPages,
            Integer currentPage) {
        this.items = items;
        this.totalItems = totalItems;
        this.itemPerPage = itemPerPage;
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

    public Integer getItemPerPage() {
        return this.itemPerPage;
    }

    public void setItemPerPage(Integer itemPerPage) {
        this.itemPerPage = itemPerPage;
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
