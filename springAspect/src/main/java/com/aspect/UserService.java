package com.aspect;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void getUserById(Long id) {
        System.out.println("Получение пользователя с ID: " + id);
    }
}