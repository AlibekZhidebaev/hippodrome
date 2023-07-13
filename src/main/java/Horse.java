import java.util.Date;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger = Logger.getLogger(Horse.class.getName());
    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            logError("Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            logError("Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            logError("Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            logError("Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;

        logDebug("Создание Horse, имя [" + name + "], скорость [" + speed + "]");
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    // Метод для записи информации об ошибке в лог
    private static void logError(String message) {
        Date currentDate = new Date();
        String formattedDate = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS,%1$tL", currentDate);
        String logMessage = formattedDate + " ERROR Horse: " + message;
        logger.severe(logMessage);
    }

    // Метод для записи отладочной информации в лог
    private static void logDebug(String message) {
        Date currentDate = new Date();
        String formattedDate = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS,%1$tL", currentDate);
        String logMessage = formattedDate + " DEBUG Horse: " + message;
        logger.fine(logMessage);
    }
}
