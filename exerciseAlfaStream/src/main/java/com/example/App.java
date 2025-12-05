package com.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Группировка и сортировка сотрудников по отделам
 * <p>
 * **Условие:**
 * <p>
 * Дан список сотрудников: `record Employee(String name, String department, int salary) {}`
 * <p>
 * Реализуйте метод: `Map<String, List<Employee>> groupAndSortByDepartment(List<Employee> employees)`
 * <p>
 * Метод должен вернуть карту:
 * <p>
 * - ключ — название отдела (department)
 * - значение — отсортированный список сотрудников по убыванию зарплаты
 * - отделы без сотрудников должны быть исключены
 * <p>
 * Пример входных данных:
 * <p>
 * List.of(
 * new Employee("Alice", "IT", 100000),
 * new Employee("Bob", "HR", 80000),
 * new Employee("Charlie", "IT", 120000),
 * new Employee("Diana", "Finance", 90000),
 * new Employee("Eve", "HR", 85000)
 * )
 * <p>
 * {
 * "IT": [Charlie, Alice],
 * "HR": [Eve, Bob],
 * "Finance": [Diana]
 * }
 * <p>
 * **Требования:**
 * <p>
 * - Использовать Java 8+ Stream API
 * - Не модифицировать исходный список сотрудников
 * - Решение должно быть лаконичным и читаемым
 */
public class App {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 100000),
                new Employee("Bob", "HR", 80000),
                new Employee("Charlie", "IT", 120000),
                new Employee("Diana", "Finance", 90000),
                new Employee("Eve", "HR", 85000)
        );

        Map<String, List<Employee>> stringListMap = groupAndSortByDepartment(employees);

        System.out.println(stringListMap);

    }

    private static Map<String, List<Employee>> groupAndSortByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> result = new HashMap<>();

        for (Employee employee : employees) {
            if (employee.department() != null) {
                List<Employee> list = result.get(employee.department());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(employee);
                result.put(employee.department(), list);
            }
        }

        result.forEach((k, v) -> v.sort((o1, o2) -> o2.salary() - o1.salary()));

        return employees.stream()
                .filter(item -> Objects.nonNull(item.department))
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                employees1 -> employees1.stream().sorted(Comparator.comparingInt(Employee::salary).reversed()).toList()
                        )
                ));
    }


    record Employee(String name, String department, int salary) {
    }
}
