package com.tus.ca.ride.hailing.handler;

import com.tus.ca.ride.hailing.model.Driver;
import com.tus.ca.ride.hailing.service.Ride;
import com.tus.ca.ride.hailing.service.RideCompletion;
import com.tus.ca.ride.hailing.service.RideRequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RideAnalyzer {

    private List<Ride> rides;
    private List<Driver> drivers;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public RideAnalyzer(List<Ride> rides, List<Driver> drivers) {
        this.rides = rides;
        this.drivers = drivers;
    }

    public void displayFormattedRideTimes() {
        System.out.println("\nListing all ride times (Formatted):");
        rides.stream()
                .forEach(r -> System.out.println(r.rideTime().format(DATE_TIME_FORMATTER)));
    }

    public void groupRidesByDriver() {
        System.out.println("Grouping rides by driver...");
        Map<String, List<Ride>> ridesByDriver = rides.stream()
                .collect(Collectors.groupingBy(Ride::driverId));
        System.out.println("Rides grouped by driver: " + ridesByDriver);
    }

    public void partitionRides() {
        System.out.println("\nPartitioning rides into requests and completions...");
        Map<Boolean, List<Ride>> partitionedRides = rides.stream()
                .collect(Collectors.partitioningBy(
                        r -> switch(r) {
                            case RideRequest _ -> true;
                            case RideCompletion _ -> false;
                        }
                ));
        System.out.println("Partitioned rides: " + partitionedRides);
    }

    public void findRideWithHighestFare() {
        System.out.println("\nFinding the ride with the highest fare...");
        rides.stream()
                .filter(r -> r instanceof RideCompletion)
                .map(r -> (RideCompletion) r)
                .max(Comparator.comparing(RideCompletion::fare))
                .ifPresent(r -> System.out.println("Highest fare ride: " + r));
    }

    public void findRideWithLowestFare() {
        System.out.println("\nFinding the ride with the lowest fare...");
        rides.stream()
                .filter(r -> r instanceof RideCompletion)
                .map(r -> (RideCompletion) r)
                .min(Comparator.comparing(RideCompletion::fare))
                .ifPresent(r -> System.out.println("Lowest fare ride: " + r));
    }

    public void countTotalRideRequests() {
        System.out.println("\nCounting total ride requests...");
        long totalRideRequests = rides.stream()
                .filter(r -> r instanceof RideRequest)
                .count();
        System.out.println("Total ride requests: " + totalRideRequests);
    }

    public void findAnyRide() {
        System.out.println("\nFinding any ride...");
        rides.stream()
                .findAny()
                .ifPresent(r -> System.out.println("Found a ride: " + r));
    }

    public void findFirstRide() {
        System.out.println("\nFinding the first ride...");
        rides.stream()
                .findFirst()
                .ifPresent(r -> System.out.println("First ride: " + r));
    }

    public void validateRides() {
        System.out.println("\nValidating rides...");
        boolean allRidesAreValid = rides.stream()
                .allMatch(r -> drivers.stream()
                        .anyMatch(d -> d.driverId().equals(r.driverId())));
        System.out.println("Are all rides valid? " + allRidesAreValid);
    }

    public void checkAnyRideByDriver(String driverId) {
        System.out.println("\nChecking if any ride is by driver '" + driverId + "'...");
        boolean anyRideByDriver = rides.stream()
                .anyMatch(r -> driverId.equals(r.driverId()));
        System.out.println("Any ride by driver '" + driverId + "': " + anyRideByDriver);
    }

    public void checkNoRideByDriver(String driverId) {
        System.out.println("\nChecking if no ride is by driver '" + driverId + "'...");
        boolean noRideByDriver = rides.stream()
                .noneMatch(r -> driverId.equals(r.driverId()));
        System.out.println("No ride by driver '" + driverId + "': " + noRideByDriver);
    }

    public void listAllRideTimes() {
        System.out.println("\nListing all ride times:");
        rides.stream()
                .forEach(r -> System.out.println(r.rideTime()));
    }

    public void mapDriverIdsToTotalRides() {
        System.out.println("\nCreating a map of driver ID to total rides...");
        Map<String, Long> rideCountsByDriver = rides.stream()
                .collect(Collectors.toMap(
                        Ride::driverId,
                        r -> 1L,
                        Long::sum
                ));
        System.out.println("Ride counts by driver: " + rideCountsByDriver);
    }

    public void filterDistinctDriverIds() {
        System.out.println("\nFiltering distinct driver IDs...");
        List<String> distinctDriverIds = rides.stream()
                .map(Ride::driverId)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Distinct driver IDs: " + distinctDriverIds);
    }

    public void listFirstThreeRides() {
        System.out.println("\nListing the first 3 rides...");
        List<Ride> firstThreeRides = rides.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("First three rides: " + firstThreeRides);
    }

    public void sortRidesByTime() {
        System.out.println("\nSorting rides by time...");
        List<Ride> sortedRides = rides.stream()
                .sorted(Comparator.comparing(Ride::rideTime))
                .collect(Collectors.toList());
        System.out.println("Rides sorted by time: " + sortedRides);
    }

    public void calculateTimeBetweenRides() {
        if (rides.size() < 2) {
            System.out.println("Not enough rides to calculate time gaps.");
            return;
        }

        // Sort rides by time
        List<Ride> sortedRides = rides.stream()
                .sorted(Comparator.comparing(Ride::rideTime))
                .collect(Collectors.toList());

        // Calculate the time difference between consecutive rides
        for (int i = 0; i < sortedRides.size() - 1; i++) {
            Ride currentRide = sortedRides.get(i);
            Ride nextRide = sortedRides.get(i + 1);

            Duration gap = Duration.between(currentRide.rideTime(), nextRide.rideTime());
            System.out.println("Time gap between Ride " + currentRide.rideId() + " and Ride " + nextRide.rideId() +  gap.toMinutes() + " minutes");
        }
    }

    public void calculateRideDuration(RideCompletion rideCompletion) {
        LocalDateTime startTime = rideCompletion.rideTime(); // Assume this is the ride start time
        LocalDateTime endTime = LocalDateTime.now(); // Simulate the end time as now

        // Calculate the duration between start and end times
        Duration duration = Duration.between(startTime, endTime);

        // Display the duration in hours and minutes
        System.out.println("Ride duration: " + duration.toHoursPart() + " hours and " + duration.toMinutesPart() + " minutes");
    }

    public void filterRidesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Ride> ridesInRange = rides.stream()
                .filter(r -> !r.rideTime().isBefore(startDate) && !r.rideTime().isAfter(endDate)) // Filter within range
                .collect(Collectors.toList());

        System.out.println("Rides within the date range:");
        ridesInRange.forEach(r -> System.out.println(r));
    }


    public void displayRideTimesInTimeZone(String zone) {
        ZoneId zoneId = ZoneId.of(zone);
        System.out.println("Ride times in time zone: " + zone);

        rides.stream()
                .forEach(r -> {
                    ZonedDateTime zonedDateTime = r.rideTime().atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId);
                    System.out.println(zonedDateTime.format(DATE_TIME_FORMATTER));
                });
    }
}
