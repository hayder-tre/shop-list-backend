package com.deno.shoplist.service;

import com.deno.shoplist.controller.beans.ItemRequest;
import com.deno.shoplist.errors.ResponseError;
import com.deno.shoplist.service.validation.ValidItem;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidationService {

    public Either<ResponseError, ValidItem> validateItemRequest(final ItemRequest itemRequest){
        var validItem = ValidItem.validate(itemRequest);
        if(validItem.isValid()){
            return Either.right(validItem.get());
        }
        return Either.left(new ResponseError(validItem.getError(), HttpStatus.BAD_REQUEST));
    }
}
