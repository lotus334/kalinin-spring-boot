package com.example.core.notificationSender;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class NotificationQueueService {

    private static final Queue<String> queue = new LinkedBlockingQueue<>();

    private NotificationQueueService() {}

    public static NotificationQueueService getInstance() {
        return NotificationHolder.INSTANCE;
    }

    private static class NotificationHolder {
        private static final NotificationQueueService INSTANCE = new NotificationQueueService();
    }

    public void add(String message) {
        queue.add(message);
    }

    public void process() {
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}