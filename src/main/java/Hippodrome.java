import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger logger = Logger.getLogger(Hippodrome.class.getName());
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            logError("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logError("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logDebug("Создание Hippodrome, лошадей [" + horses.size() + "]");
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
    // Логирование отладочной информации уровня DEBUG


    // Метод для записи информации об ошибке в лог
    private void logError(String message) {
        Date currentDate = new Date();
        String formattedDate = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS,%1$tL", currentDate);
        String logMessage = formattedDate + " ERROR Hippodrome: " + message;
        logger.severe(logMessage);
    }
    private void logDebug(String message) {
        Date currentDate = new Date();
        String formattedDate = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS,%1$tL", currentDate);
        String logMessage = formattedDate + " DEBUG Hippodrome: " + message;
        logger.fine(logMessage);
    }
}
