package com.tus.ca.ride.hailing.exceptions;

import java.util.function.Consumer;

public class ExceptionHandler {

    // Handle exceptions using a Consumer
    public void handleException(Consumer<Exception> consumer, Exception e) {
        consumer.accept(e);
    }

    // Utility method for safe executions
    public void executeSafely(Runnable runnable, String contextMessage, Consumer<Exception> consumer) {
        try {
            runnable.run();
        } catch (Exception e) {
            System.err.println("Context: " + contextMessage);
            consumer.accept(e);
        }
    }
}
