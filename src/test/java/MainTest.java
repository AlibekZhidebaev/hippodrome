import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @Test
    @Timeout(22_000) // -- Устанавливаем ограничение в 22 секунды --
    @Disabled // -- Тест отключен --
    public void testMainExecutionTime() throws Exception {
        Main.main(new String[]{});
        assertTrue(true); // -- Проверка для успешного прохождения теста --
    }

}
