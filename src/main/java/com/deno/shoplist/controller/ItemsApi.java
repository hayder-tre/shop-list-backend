package com.deno.shoplist.controller;

import com.deno.shoplist.controller.beans.ItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;


public interface ItemsApi {
    String BASE_PATH = "/api/items";

    @GetMapping(BASE_PATH)
    Mono<ResponseEntity> getItems();

    @GetMapping(BASE_PATH + "/{id}")
    Mono<ResponseEntity> getItem(@PathVariable("id") final String id);

    @PostMapping(BASE_PATH)
    Mono<ResponseEntity> createItem(@RequestBody final ItemRequest body);

}
