import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {

    public static void main(String[] args) throws Exception {
        List<HorseTest> horses = List.of(
                new HorseTest("Буцефал", 2.4),
                new HorseTest("Туз Пик", 2.5),
                new HorseTest("Зефир", 2.6),
                new HorseTest("Пожар", 2.7),
                new HorseTest("Лобстер", 2.8),
                new HorseTest("Пегас", 2.9),
                new HorseTest("Вишня", 3)
        );
        HippodromeTest hippodrome = new HippodromeTest(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println("Победил " + winnerName + "!");
    }

    private static void watch(HippodromeTest hippodrome) throws Exception {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }
}
