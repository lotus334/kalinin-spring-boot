package com.example.core.annotation.notificationSender;

public interface NotificationSender {

    void sendNotification(String message);

    // sent
    boolean isAvailable();
}
