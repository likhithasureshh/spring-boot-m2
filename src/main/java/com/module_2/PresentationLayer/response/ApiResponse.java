package com.module_2.PresentationLayer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.module_2.PresentationLayer.advices.ApiError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    @JsonFormat(timezone = "hh:mm:ss dd-MM-yy")
    private LocalDateTime timeStamp;
    private T data;
    private ApiError apiError;

    public ApiResponse()
    {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError apiError) {
        this();
        this.apiError = apiError;
    }
}
