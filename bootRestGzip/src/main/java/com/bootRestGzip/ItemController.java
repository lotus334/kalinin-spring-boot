package com.bootRestGzip;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    com.bootRestGzip.ItemRepository itemRepository;

    @GetMapping("/items")
    List<com.bootRestGzip.Item> all() {
        return itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    com.bootRestGzip.Item getById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new com.bootRestGzip.ItemNotFoundException(id));
    }

    @PostMapping("/items")
    com.bootRestGzip.Item createNew(@Valid @RequestBody com.bootRestGzip.Item newItem) {
        return itemRepository.save(newItem);
    }

    @DeleteMapping("/items/{id}")
    void delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/items/{id}")
    com.bootRestGzip.Item updateOrCreate(@RequestBody com.bootRestGzip.Item newItem, @PathVariable Long id) {
        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    return itemRepository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return itemRepository.save(newItem);
                });
    }
}