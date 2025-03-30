package com.tus.ca.ride.hailing.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class DirectoryUtility {

    // List all files in the  directory
    public void listFilesInDirectory(String directoryPath) {
        Path dirPath = Path.of(directoryPath);
        try (Stream<Path> files = Files.list(dirPath)) {
            files.filter(Files::isRegularFile) // Only regular files
                    .forEach(file -> System.out.println(file.getFileName()));
        } catch (IOException e) {
            ExceptionUtility.logException(e);
        }
    }

    // Watch the directory for changes
    public void watchDirectory(String directoryPath) {
        Path path = Path.of(directoryPath);

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);

            System.out.println("Watching directory: " + path);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    // Handle directory events using a switch expression
                    String message = switch (event.kind().name()) {
                        case "ENTRY_CREATE" -> "File created: " + event.context();
                        case "ENTRY_MODIFY" -> "File modified: " + event.context();
                        default -> "Unknown event: " + event.context();
                    };
                    System.out.println(message);
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            ExceptionUtility.logException(e);
        }
    }


}
