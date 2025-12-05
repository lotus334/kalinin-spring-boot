package com.example.transactionalSelfInvocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public void mainFunction(Feedback feedback) {
        System.out.println("Transaction name from FeedbackService#processFeedback: " + TransactionSynchronizationManager.getCurrentTransactionName());

        privateFunction(feedback);
        notificationService.innerFunction();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void privateFunction(Feedback feedback) {
        System.out.println("Transaction name from FeedbackService#addFeedback: " + TransactionSynchronizationManager.getCurrentTransactionName());

        if (feedback.getText() == null || feedback.getText().isEmpty()) {
            throw new IllegalArgumentException("Feedback text cannot be null or empty");
        }
        feedbackRepository.save(feedback);
    }
}
