package com.example.collection.singleLinkedList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

public class MyLinkedList implements MyList {

    private Node last;

    @Getter
    private int size;

    @Override
    public void add(int value) {
        if (size == 0) {
            last = new Node(value);
            size++;
        } else {
            Node newPrev = last;
            last = new Node(value, newPrev);
            size++;
        }
    }

    @Override
    public boolean remove(int value) {
        if (last == null || size == 0) {
            return false;
        }

        if (last.value == value) {
            Node toRemove = last;
            last = last.prev;
            toRemove.prev = null;
            size--;
            return true;
        }

        Node current = last;
        Node prev = last.prev;
        while (prev != null) {
            if (prev.value == value) {
                current.prev = prev.prev;
                prev.prev = null;
                size--;
                return true;
            }
            current = prev;
            prev = current.prev;
        }
        return false;
    }

    @Override
    public boolean contains(int value) {
        Node prev = last;
        while (prev != null) {
            if (prev.value == value) {
                return true;
            }
            prev = prev.prev;
        }
        return false;
    }

    @Override
    public void printList() {
        int[] array = new int[size];
        if (last != null) {
            int counter = size - 1;
            array[counter--] = last.value;
            Node prev = last.prev;
            while (prev != null) {
                array[counter--] = prev.value;
                prev = prev.prev;
            }
        }

        System.out.println(Arrays.toString(array));
    }

    @RequiredArgsConstructor
    @AllArgsConstructor
    private static class Node {
        private final int value;
        Node prev;
    }
}
