import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;

public class HippodromeTest {

    private final List<HorseTest> horses;

    public HippodromeTest(List<HorseTest> horses) {
        if (isNull(horses)) {
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
    }

    public List<HorseTest> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(HorseTest::move);
    }

    public HorseTest getWinner() {
        return horses.stream()
                .max(Comparator.comparing(HorseTest::getDistance))
                .get();
    }
}
