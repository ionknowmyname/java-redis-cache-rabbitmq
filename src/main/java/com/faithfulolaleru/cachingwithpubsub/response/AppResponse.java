package com.faithfulolaleru.cachingwithpubsub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T> implements Serializable {

    private static final long serialVersionUID = 7156526077883281625L;

    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    private T data;
}
