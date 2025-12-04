package com.example.core.customArrayList;

import java.util.Arrays;

public class MyArrayList implements MyList {

    private int[] array = new int[10];
    private int size = 0;

    @Override
    public void add(int value) {
        if (array.length == size) {
            int[] tempArray = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }
        array[size++] = value;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public int get(int index) {
        return array[index];
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
