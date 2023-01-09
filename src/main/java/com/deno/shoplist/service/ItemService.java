package com.deno.shoplist.service;

import com.deno.shoplist.errors.ResponseError;
import com.deno.shoplist.model.Item;
import com.deno.shoplist.service.validation.ValidItem;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface ItemService {

    Mono<List<Item>> getItems();
    Either<ResponseError, Mono<Item>> getItem(final String id);
    Mono<Item> createItem(ValidItem validItem);
}
