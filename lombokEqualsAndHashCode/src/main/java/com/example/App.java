package com.example;

import com.example.entity.Item;
import com.example.entity.ItemCorrect;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Set<Item> itemsIncorrect = new HashSet<>();

        Item item = Item.builder().value("Hello, World!").build();
        itemsIncorrect.add(item);

        item.setValue("Hello, World 2!");
        itemsIncorrect.add(item);

        Set<ItemCorrect> itemsCorrect = new HashSet<>();

        ItemCorrect itemCorrect = ItemCorrect.builder().value("Hello, World!").build();
        itemsCorrect.add(itemCorrect);

        itemCorrect.setValue("Hello, World 2!");
        itemsCorrect.add(itemCorrect);

        if (itemsCorrect.size() != 1) {
            throw new RuntimeException("Items size is not 1");
        }

        if (itemsIncorrect.size() == 2) {
            throw new RuntimeException("Items size is 2");
        }
    }
}