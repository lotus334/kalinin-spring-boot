package com.example.collection.customArrayList;

/**
 * Задача 1: Реализация собственного ArrayList (задача встречалась в СБЕР)
 *
 * Создай класс `MyArrayList`, который будет реализовывать основные функции ArrayList,
 * такие как добавление, удаление, поиск и получение значений по индексу.
 * Класс должен динамически увеличивать размер массива во время выполнения, если у него не хватает места для новых элементов.
 *
 * **Требования:**
 *
 * - В классе должны быть следующие методы следующие методы:
 *     - `void add(int value)`– добавляет элемент в конец списка.
 *     - `void remove(int index)`– удаляет элемент по индексу.
 *     - `int get(int index)`– возвращает элемент по индексу.
 *     - `boolean contains(int value)`– возвращает`true`, если список содержит элемент со значением`value`, в противном случае`false`.
 *     - `int size()`– возвращает текущее количество элементов в списке.
 */
public class Main {

    public static void main(String[] args) {
        MyList list = new MyArrayList();
        System.out.println(list);
        System.out.println(list.size());

        list.add(0);
        list.add(1);
        System.out.println(list);
        System.out.println(list.size());

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        System.out.println(list.size());

        System.out.println(list.get(1));

        System.out.println(list.contains(9));

        for (int i = 0; i < 12; i++) {
            list.remove(i);
        }
        System.out.println(list);
        System.out.println(list.size());
    }
}
