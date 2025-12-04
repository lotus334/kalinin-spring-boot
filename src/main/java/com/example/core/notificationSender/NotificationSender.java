package com.example.core.notificationSender;

public interface NotificationSender {

    void sendNotification(String message);

    // sent
    boolean isAvailable();
}
