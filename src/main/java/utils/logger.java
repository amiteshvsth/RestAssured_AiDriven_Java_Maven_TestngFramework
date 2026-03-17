package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.println("[" + LocalDateTime.now().format(TIMESTAMP) + "] INFO: " + message);
    }

    public static void error(String message) {
        System.err.println("[" + LocalDateTime.now().format(TIMESTAMP) + "] ERROR: " + message);
    }
}
