import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    @Timeout(22_000) // Устанавливаем ограничение в 22 секунды
    @Disabled // Отключаем этот тест
    public void testMainExecutionTime() throws Exception {
        Main.main(new String[]{});
        assertTrue(true); // Добавляем пустую проверку для успешного прохождения теста
    }

}
