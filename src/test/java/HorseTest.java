import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class HorseTest {

    // -- Тесты конструкторов --
    @Test
     protected void testConstructorAndMethods1() {
         assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0, 0)); }
    @Test
    protected void testConstructorAndMethods() {
          Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 0, 0));
            assertEquals("Name cannot be null.", exception.getMessage());
    }
     @ParameterizedTest
     @ValueSource(strings = {"", " ", "\t", "\n"})
     protected void testConstructorWithEmptyOrWhitespaceParameter(String parameter) {
         assertThrows(IllegalArgumentException.class, () -> new Horse(parameter, 0, 0));
     }
     @ParameterizedTest
     @ValueSource(strings = {"", " ", "\t", "\n"})
     protected void testConstructorWithEmptyOrWhitespaceParameter2(String parameter) {
         Throwable exception = assertThrows(IllegalArgumentException.class, () ->
               new Horse(parameter, 0, 0));
         assertEquals("Name cannot be blank.", exception.getMessage());
     }
     @Test
     protected void testConstructorWithNegativeNumberParameter() {
         assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 0));
     }
     @Test
     protected void testConstructorWithNegativeNumberParameter2() {
         Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                 new Horse("name", -1, 0));
         assertEquals("Speed cannot be negative.", exception.getMessage());
     }

    // -- Тесты методов --
     @Test
     protected void testConstructorWithNegativeNumberParameter3() {
         assertThrows(IllegalArgumentException.class, () -> new Horse("name", 0, -1));
     }
     @Test
     protected void testConstructorWithNegativeNumberParameter4() {
         Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                 new Horse("name", 0, -1));
         assertEquals("Distance cannot be negative.", exception.getMessage());
     }

     // -- Тесты методов --

     @Test
     public void testGetName() {
         assertEquals("MyHorse", new Horse("MyHorse", 0, 0).getName());
     }
     @Test
     public void testGetSpeed() {
         assertEquals(1, new Horse("name", 1, 0).getSpeed());
     }
     @Test
     public void testGetDistance() {
         assertEquals(10, new Horse("name", 0, 10).getDistance());
     }

     // -- Mock Test --
     @Test
     public void testMove() {
         try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
             new Horse("horseName", 0, 0).move();
             mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
         }
     }
    @Test
    public void testGetDistance3() {
        mockStatic(Horse.class);
        double speed = 10;
        double startDistance = 100;
        double expectedRandomValue = 0.5;
        when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(expectedRandomValue);
        double endDistance = startDistance + speed * expectedRandomValue;
        Horse horse = new Horse("horseName",speed,startDistance);
        horse.move();
        assertEquals(endDistance, horse.getDistance());
    }
}

