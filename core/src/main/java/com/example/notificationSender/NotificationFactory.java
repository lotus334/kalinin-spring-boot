package com.example.notificationSender;

public class NotificationFactory {

    static public NotificationSender getEmailNotificationSender() {
        return new NotificationSender() {
            private boolean available = true;

            @Override
            public void sendNotification(String message) {
                available = false;
                NotificationQueueService.getInstance().add(message);
            }

            @Override
            public boolean isAvailable() {
                return available;
            }
        };
    }
}
