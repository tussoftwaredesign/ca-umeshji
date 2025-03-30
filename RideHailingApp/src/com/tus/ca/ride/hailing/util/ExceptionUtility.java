package com.tus.ca.ride.hailing.util;

public class ExceptionUtility {
    public static void logException(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace();
    }
}
