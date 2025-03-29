package com.tus.ca.ride.hailing.app;

import com.tus.ca.ride.hailing.exceptions.ExceptionHandler;
import com.tus.ca.ride.hailing.model.Driver;
import com.tus.ca.ride.hailing.service.Ride;
import com.tus.ca.ride.hailing.service.RideCompletion;
import com.tus.ca.ride.hailing.service.RideRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

    /**
     * Main class for the Ride Hailing application.
     * Simulates ride requests, ride completions, and analyzes rides using Stream APIs.
     */
    public class Main {

        private final List<Ride> rides = new ArrayList<>();
        private final ExecutorService executor = Executors.newFixedThreadPool(4);
        private final List<Driver> drivers = List.of(
                new Driver("D1", "John Doe", "Sedan", 4.8),
                new Driver("D2", "Jane Smith", "SUV", 4.9),
                new Driver("D3", "Alice Johnson", "Bike", 4.7)
        );
        private final ExceptionHandler exceptionHandler = new ExceptionHandler();

        public static void main(String[] args) {
            new Main().run();
        }

        /**
         * Entry point to run the application.
         */
        public void run() {
            // Validate driver IDs using Predicate
            Predicate<String> isDriverValid = driverId -> drivers.stream()
                    .anyMatch(driver -> driver.driverId().equals(driverId));

            // Suppliers to generate ride requests and completions dynamically
            Supplier<RideRequest> createRideRequest = () -> new RideRequest("R1", "U1", "D1", LocalDateTime.now());
            Supplier<RideCompletion> createRideCompletion = () -> new RideCompletion("R1", "U1", "D1", LocalDateTime.now(), 25.0);

            // Consumer to process and validate rides
            Consumer<Ride> processRide = ride -> {
                if (!isDriverValid.test(ride.driverId())) {
                    throw new IllegalArgumentException("Invalid driverId: " + ride.driverId());
                }
                rides.add(ride);
                System.out.println("Processed ride: " + ride);
            };

            // Tasks for concurrent ride processing
            List<Callable<Void>> tasks = List.of(
                    () -> {
                        exceptionHandler.executeSafely(() -> processRide.accept(createRideRequest.get()), "RideRequest R1", this::logException);
                        return null;
                    },
                    () -> {
                        exceptionHandler.executeSafely(() -> processRide.accept(createRideCompletion.get()), "RideCompletion R1", this::logException);
                        return null;
                    }
            );

            // Execute tasks and shut down executor
            executeTasks(tasks);

            // Analyze rides after processing
            analyzeRides();
        }

        /**
         * Executes tasks concurrently using the ExecutorService.
         *
         * @param tasks List of tasks to execute
         */
        private void executeTasks(List<Callable<Void>> tasks) {
            try {
                executor.invokeAll(tasks);
            } catch (InterruptedException e) {
                exceptionHandler.handleException(this::logException, e);
                Thread.currentThread().interrupt();
            } finally {
                shutdownExecutor();
            }
        }

        /**
         * Gracefully shuts down the ExecutorService.
         */
        private void shutdownExecutor() {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                    System.err.println("Forcing ExecutorService shutdown...");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                logException(e);
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Logs exceptions with context.
         *
         * @param e The exception to log
         */
        private void logException(Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        /**
         * Analyzes and summarizes ride data using Stream APIs.
         */
        private void analyzeRides() {
            System.out.println("\n--- Ride Analysis ---");

            // 1. Group rides by driver ID
            System.out.println("Grouping rides by driver...");
            Map<String, List<Ride>> ridesByDriver = rides.stream()
                    .collect(Collectors.groupingBy(Ride::driverId));
            System.out.println("Rides grouped by driver: " + ridesByDriver);

            // 2. Partition rides into RideRequests and RideCompletions
            System.out.println("\nPartitioning rides into requests and completions...");
            Map<Boolean, List<Ride>> partitionedRides = rides.stream()
                    .collect(Collectors.partitioningBy(r -> r instanceof RideRequest));
            System.out.println("Partitioned rides: " + partitionedRides);

            // 3. Find the ride with the highest fare
            System.out.println("\nFinding the ride with the highest fare...");
            rides.stream()
                    .filter(r -> r instanceof RideCompletion)
                    .map(r -> (RideCompletion) r)
                    .max(Comparator.comparing(RideCompletion::fare))
                    .ifPresent(r -> System.out.println("Highest fare ride: " + r));

            // 4. Find the ride with the lowest fare
            System.out.println("\nFinding the ride with the lowest fare...");
            rides.stream()
                    .filter(r -> r instanceof RideCompletion)
                    .map(r -> (RideCompletion) r)
                    .min(Comparator.comparing(RideCompletion::fare))
                    .ifPresent(r -> System.out.println("Lowest fare ride: " + r));

            // 5. Count total ride requests
            System.out.println("\nCounting total ride requests...");
            long totalRideRequests = rides.stream()
                    .filter(r -> r instanceof RideRequest)
                    .count();
            System.out.println("Total ride requests: " + totalRideRequests);

            // 6. Find any ride
            System.out.println("\nFinding any ride...");
            rides.stream()
                    .findAny()
                    .ifPresent(r -> System.out.println("Found a ride: " + r));

            // 7. Find the first ride
            System.out.println("\nFinding the first ride...");
            rides.stream()
                    .findFirst()
                    .ifPresent(r -> System.out.println("First ride: " + r));

            // 8. Check if all rides are valid
            System.out.println("\nValidating rides...");
            boolean allRidesAreValid = rides.stream()
                    .allMatch(r -> drivers.stream().anyMatch(d -> d.driverId().equals(r.driverId())));
            System.out.println("Are all rides valid? " + allRidesAreValid);

            // 9. Check if any ride is by driver "D1"
            System.out.println("\nChecking if any ride is by driver 'D1'...");
            boolean anyRideByDriverD1 = rides.stream()
                    .anyMatch(r -> "D1".equals(r.driverId()));
            System.out.println("Any ride by driver 'D1': " + anyRideByDriverD1);

            // 10. Check if no ride is by driver "D99"
            System.out.println("\nChecking if no ride is by driver 'D99'...");
            boolean noRideByDriverD99 = rides.stream()
                    .noneMatch(r -> "D99".equals(r.driverId()));
            System.out.println("No ride by driver 'D99': " + noRideByDriverD99);

            // 11. Display all ride times
            System.out.println("\nListing all ride times:");
            rides.stream()
                    .forEach(r -> System.out.println(r.rideTime()));

            // 12. Map of driver IDs to total rides
            System.out.println("\nCreating a map of driver ID to total rides...");
            Map<String, Long> rideCountsByDriver = rides.stream()
                    .collect(Collectors.toMap(
                            Ride::driverId,
                            r -> 1L,
                            Long::sum
                    ));
            System.out.println("Ride counts by driver: " + rideCountsByDriver);

            // 13. Display distinct driver IDs
            System.out.println("\nFiltering distinct driver IDs...");
            List<String> distinctDriverIds = rides.stream()
                    .map(Ride::driverId)
                    .distinct()
                    .collect(Collectors.toList());
            System.out.println("Distinct driver IDs: " + distinctDriverIds);

            // 14. Limit to the first 3 rides
            System.out.println("\nListing the first 3 rides...");
            List<Ride> firstThreeRides = rides.stream()
                    .limit(3)
                    .collect(Collectors.toList());
            System.out.println("First three rides: " + firstThreeRides);

            // 15. Sort rides by ride time
            System.out.println("\nSorting rides by time...");
            List<Ride> sortedRides = rides.stream()
                    .sorted(Comparator.comparing(Ride::rideTime))
                    .collect(Collectors.toList());
            System.out.println("Rides sorted by time: " + sortedRides);
        }
    }