package com.example.core.singleLinkedList;

import com.example.core.collection.singleLinkedList.MyLinkedList;
import com.example.core.collection.singleLinkedList.MyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    MyList list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList();
    }

    @Test
    void add() {
        list.add(1);

        assertEquals(1, list.getSize());

        list.add(2);

        list.add(3);

        assertEquals(3, list.getSize());

        list.printList();
    }

    @Test
    void remove() {

        list.add(1);

        list.add(2);

        list.add(3);

        list.remove(2);

        list.printList();

        list.remove(3);

        list.printList();

        list.remove(1);

        list.printList();

        list.add(4);

        list.add(5);

        list.add(6);

        list.printList();
    }

    @Test
    void contains() {
        list.add(1);

        list.add(2);

        list.add(3);

        assertTrue(list.contains(1));
        assertTrue(list.contains(3));
        assertTrue(list.contains(2));
        assertFalse(list.contains(0));
        assertFalse(list.contains(4));
    }

    @Test
    void printList() {
    }
}