package com.mcs.ipm.controller;

import com.mcs.ipm.entity.Item;
import com.mcs.ipm.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemRepository itemRepository;

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> getAllItems() {
        logger.info("Inside getAllItems");
        List<Item> items = itemRepository.findAll();
        log.info(items.size()+" Items(s) found!");
        return items;
    }

    @GetMapping(value="/items/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getItemByName(@PathVariable String itemName) {
        logger.info("Inside getItemByName");
        Item item = itemRepository.findByName(itemName);
        if(item == null)
            throw new RuntimeException(itemName+" - Item not found");
        return item;
    }

    @PostMapping("/items")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item newItem = itemRepository.saveAndFlush(item);
        log.info(newItem.getId()+" Item added!");
        return ResponseEntity.ok(item);

    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable long itemId) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (!optionalItem.isPresent())
            return ResponseEntity.notFound().build();

        item.setId(itemId);
        itemRepository.save(item);

        log.info(itemId+" Item updated!");
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/items/{itemId}")
    public void removeItem(@PathVariable long itemId) {
        itemRepository.deleteById(itemId);
        log.info(itemId+" Item removed!");
    }

    

}
