package com.blog.dto.response;

public class SuccessResponseDTO {
    private Integer code;
    private String status;
    private Object data;

    public SuccessResponseDTO(Integer code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
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

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
