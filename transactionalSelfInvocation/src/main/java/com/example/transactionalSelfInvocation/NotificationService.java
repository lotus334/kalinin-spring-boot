package com.example.transactionalSelfInvocation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class NotificationService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerFunction() {
        System.out.println("Transaction name from FeedbackService#addFeedback: " + TransactionSynchronizationManager.getCurrentTransactionName());
    }
}