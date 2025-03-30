package com.tus.ca.ride.hailing.util;

import com.tus.ca.ride.hailing.enums.VehicleType;
import com.tus.ca.ride.hailing.model.Driver;
import com.tus.ca.ride.hailing.service.Ride;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtility {
    public static List<Driver> readDriversFromFile(String fileName) {
        Path filePath = Path.of(fileName);

        try {
            return Files.lines(filePath)
                    .map(line -> {
                        String[] parts = line.split(",");
                        return new Driver(parts[0], parts[1], VehicleType.valueOf(parts[2].toUpperCase()), Double.parseDouble(parts[3]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            ExceptionUtility.logException(e);
            return List.of(); // Return an empty list in case of failure
        }
    }

    public static void writeRidesToFile(String fileName, List<Ride> rides) {
        Path filePath = Path.of(fileName);

        try (var writer = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            writer.write("--- Ride Analysis ---\n");

            // Write each ride to the file
            for (Ride ride : rides) {
                writer.write(ride.toString() + "\n");
            }

            System.out.println("Ride data has been written to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            ExceptionUtility.logException(e);
        }
    }
}
