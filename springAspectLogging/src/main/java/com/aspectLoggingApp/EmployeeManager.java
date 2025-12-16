package com.aspectLoggingApp;

import org.springframework.stereotype.Service;

@Service
public class EmployeeManager {

    public String getEmployeeById(Long id) {
        // Логика для получения сотрудника из базы данных
        return "Employee with id " + id;
    }
}
