package com.example.notificationSender;

public interface NotificationSender {

    void sendNotification(String message);

    // sent
    boolean isAvailable();
}
