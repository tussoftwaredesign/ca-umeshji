package com.tus.ca.flight.reservation.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppContext {
    private final Map<String, List<?>> data;

    public AppContext() {
        this.data = new HashMap<>();
    }

    // Method to add a list of objects to the flightReservation context
    public <T> void addList(String key, List<T> list) {
        data.put(key, list);
    }

    // Method to retrieve a list of objects from the flightReservation context
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, Class<T> clazz) {
        return (List<T>) data.get(key);
    }

    // Method to check if a list exists in the flightReservation context
    public boolean hasList(String key) {
        return data.containsKey(key);
    }

    // Method to remove a list from the context
    public void removeList(String key) {
        data.remove(key);
    }

    /*
        Holds app data in the form of <key, List<T>
        e.g.
            bookings, List.of(booking)
            passengers, List.of(passenger)
            flights, List.of(flights)
    */
}
