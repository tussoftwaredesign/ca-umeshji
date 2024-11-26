package com.tus.ca.flight.reservation.exceptions;

public class RouteNotFound extends Exception{
    public RouteNotFound(String message) {
        super(message); //invokes the constructor of the superclass (Exception)
    }
}
