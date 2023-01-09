package com.deno.shoplist.service.validation;

import com.deno.shoplist.controller.beans.ItemRequest;
import io.vavr.control.Validation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidItem {
    String title;
    String description;

    public static Validation<String, ValidItem> validate(ItemRequest itemRequest){
        return Validation
                .combine(
                        validateTitle(itemRequest.title),
                        validateDescription(itemRequest.description)
                )
                .ap(ValidItem::new)
                .mapError(s -> s.mkString(", "));
    }

    private static Validation<String, String> validateTitle(String value){
        if(value == null){
            return Validation.invalid("title cannot be null");
        }
        return Validation.valid(value);
    }

    private static Validation<String, String> validateDescription(String value){
        if(value == null){
            return Validation.invalid("Description cannot be null");
        }
        return Validation.valid(value);
    }
}
