package com.example.core.annotation.notificationSender;

/**
 * Разработай систему отправки уведомлений:
 *
 * interface NotificationSender {
 *     void sendNotification(String message);
 *     boolean isAvailable();
 * }
 *
 * 1. Создай классы EmailNotification, SMSNotification, PushNotification
 * 2. Реализуй фабрику уведомлений
 * 3. Добавь обработку ошибок
 * 4. Реализуй очередь уведомлений
 */
public class Main {

    public static void main(String[] args) {

        NotificationSender emailNotificationSender = NotificationFactory.getEmailNotificationSender();
        System.out.println(emailNotificationSender.isAvailable());
        emailNotificationSender.sendNotification("Hello");
        System.out.println(emailNotificationSender.isAvailable());

        NotificationQueueService.getInstance().process();
    }
}
