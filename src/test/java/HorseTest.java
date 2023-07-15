import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class HorseTest {

    // -- Тесты конструкторов --
    @Test
     protected void testConstructorCallExceptionTypeForFirstParameter() {
         assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0, 0)); }
    @Test
    protected void testConstructorExceptionReportingNullForFirstParameter() {
          Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 0, 0));
            assertEquals("Name cannot be null.", exception.getMessage());
    }
     @ParameterizedTest
     @ValueSource(strings = {"", " ", "\t", "\n"})
     protected void testConstructorWithEmptyOrWhitespaceForFirstParameter(String horseName) {
         assertThrows(IllegalArgumentException.class, () -> new Horse(horseName, 0, 0));
     }
     @ParameterizedTest
     @ValueSource(strings = {"", " ", "\t", "\n"})
     protected void testConstructorExceptionReportingCannotBlankForFirstParameter(String horseName) {
         Throwable exception = assertThrows(IllegalArgumentException.class, () ->
               new Horse(horseName, 0, 0));
         assertEquals("Name cannot be blank.", exception.getMessage());
     }
     @Test
     protected void testConstructorCallExceptionTypeForSecondParameter() {
         assertThrows(IllegalArgumentException.class, () -> new Horse("horseName", -1, 0));
     }
     @Test
     protected void testConstructorExceptionReportingNegativeNumberForSecondParameter() {
         Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                 new Horse("horseName", -1, 0));
         assertEquals("Speed cannot be negative.", exception.getMessage());
     }

    // -- Тесты методов --
     @Test
     protected void testConstructorCallExceptionTypeForThirdParameter() {
         assertThrows(IllegalArgumentException.class, () -> new Horse("horseName", 0, -1));
     }
     @Test
     protected void testConstructorExceptionReportingNegativeNumberForThirdParameter() {
         Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                 new Horse("horseName", 0, -1));
         assertEquals("Distance cannot be negative.", exception.getMessage());
     }

     // -- Тесты методов --

     @Test
     public void testGetName() {
         assertEquals("horseName", new Horse("horseName", 0, 0).getName());
     }
     @Test
     public void testGetSpeed() {
         assertEquals(10, new Horse("horseName", 10, 0).getSpeed());
     }
     @Test
     public void testGetDistanceParameter() {
         assertEquals(10, new Horse("horseName", 0, 10).getDistance());
     }
    @Test
    public void testGetDistanceZeroForNoParameter() {
        assertEquals(0, new Horse("horseName", 0).getDistance());
    }

    // -- Mock Test --

     @Test
     public void testMoveVerifyCallGetRandomDoubleMethodAndCheckExpressionValues() {
         try (MockedStatic<Horse> horseMock = Mockito.mockStatic(Horse.class)) {
             double speed = 10;
             double startDistance = 100;
             double expectedRandomValue = 0.5;
             Horse horse = new Horse("horseName", speed, startDistance);
             // -- Установка желаемого значения (expectedRandomValue = 0.5) при вызове метода getRandomDouble --
             when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(expectedRandomValue);
             horse.move();
             // -- Проверка вызова статического метода getRandomDouble --
             horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
             double endDistance = startDistance + speed * expectedRandomValue;
             // -- Проверка корректности вычисления выражения --
             assertEquals(endDistance, horse.getDistance());
         }
     }

}

