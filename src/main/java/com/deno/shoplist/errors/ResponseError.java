package com.deno.shoplist.errors;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ResponseError extends Exception{
    private HttpStatus statusCode;

    public ResponseError(final String message, final HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
