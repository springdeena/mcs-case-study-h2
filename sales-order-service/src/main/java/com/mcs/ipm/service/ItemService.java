package com.mcs.ipm.service;

import com.mcs.ipm.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="item-service")
public interface ItemService {

    @GetMapping("/items/{itemName}")
    public Item getItemByName(@PathVariable String itemName);
}
