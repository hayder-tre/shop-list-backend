package com.deno.shoplist.controller.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemRequest {
    public String title;
    public String description;

    @JsonCreator
    public ItemRequest(@JsonProperty("title") final String title, @JsonProperty("description") final String description) {
        this.title = title;
        this.description = description;
    }
}
