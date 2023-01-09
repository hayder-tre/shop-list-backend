package com.deno.shoplist.service;

import com.deno.shoplist.errors.ResponseError;
import com.deno.shoplist.model.Item;
import com.deno.shoplist.service.validation.ValidItem;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService{
    public List<Item> items = new ArrayList<>();
    @Override
    public Mono<List<Item>> getItems() {
        return Mono.just(items);
    }

    @Override
    public Either<ResponseError, Mono<Item>> getItem(final String item) {
        if(items.stream().anyMatch(s -> s.id().equals(item))){
            return Either.right(Mono.just(items.stream().filter(s -> s.id().equals(item)).findFirst().get()));
        }
        return Either.left(new ResponseError("Item is not found!", HttpStatus.OK));
    }

    @Override
    public Mono<Item> createItem(final ValidItem validItem) {
        var itemId = UUID.randomUUID().toString();
        items.add(new Item(itemId,validItem.getTitle(), validItem.getDescription(), new Date().toString()));
        return Mono.just(items.stream().filter(s -> s.id().equals(itemId)).findFirst().get());
    }
}
