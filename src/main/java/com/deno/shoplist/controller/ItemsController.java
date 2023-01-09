package com.deno.shoplist.controller;

import com.deno.shoplist.controller.beans.ItemRequest;
import com.deno.shoplist.service.ItemService;
import com.deno.shoplist.service.ValidationService;
import com.deno.shoplist.service.validation.ValidItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("")
@CrossOrigin("*")
public class ItemsController implements ItemsApi {

    private final ItemService itemService;
    private final ValidationService validationService;


    public ItemsController(final ItemService itemService, final ValidationService validationService) {
        this.itemService = itemService;
        this.validationService = validationService;
    }

    @Override
    public Mono<ResponseEntity> getItems() {
        return Mono.just(ResponseEntity.ok(itemService.getItems()));
    }

    @Override
    public Mono<ResponseEntity> getItem(final String id) {
        return itemService.getItem(id).fold(
                inValid -> Mono.just(ResponseEntity.status(inValid.getStatusCode()).body(inValid.getMessage())),
                valid -> Mono.just(ResponseEntity.ok(valid))
        );
    }

    @Override
    public Mono<ResponseEntity> createItem(final ItemRequest body) {
        return validationService.validateItemRequest(body)
                .fold(
                        inValid -> Mono.just(ResponseEntity.status(inValid.getStatusCode()).body(inValid.getMessage())),
                        valid -> Mono.just(ResponseEntity.ok(itemService.createItem(valid)))
                );
    }
}
