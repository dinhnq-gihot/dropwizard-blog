package com.blog.dto.response;

public class ErrorResponseDTO {
    private Integer code;
    private String status;
    private String message;

    public ErrorResponseDTO(Integer code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
