import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HippodromeTest {

 // -- Тесты конструкторов --
 @Test
 protected void testConstructorCallExceptionType() {
  assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)); }
 @Test
 protected void testConstructorExceptionReportingNull() {
  Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
  assertEquals("Horses cannot be null.", exception.getMessage());
 }
 @Test
 protected void testConstructorEmptyListException() {
  assertThrows (IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
 }
 @Test
 protected void testConstructorExceptionReportingEmptyList() {
  Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
  assertEquals("Horses cannot be empty.", exception.getMessage());
 }

 // -- Тесты методов --
 @Test
 public void testGetHorses() {
  // Создаем список из 30 разных лошадей
  List<Horse> horseList = createHorseListWithDifferentNames();
  // Создаем экземпляр класса Hippodrome с переданным списком лошадей
  Hippodrome hippodrome = new Hippodrome(horseList);
  // Получаем список лошадей с помощью метода getHorses()
  List<Horse> returnedHorses = hippodrome.getHorses();
  // Проверяем, что полученный список содержит те же объекты и в той же последовательности, что и исходный список
  assertEquals(horseList, returnedHorses);
 }
 @Test
 public void testMove() {
  // Создаем список из 50 моков лошадей
  List<Horse> horseList = createMockHorseList(50);
  // Создаем экземпляр класса Hippodrome с переданным списком моков лошадей
  Hippodrome hippodrome = new Hippodrome(horseList);
  // Вызываем метод move()
  hippodrome.move();
  // Проверяем, что метод move() вызван у всех лошадей
  for (Horse horse : horseList) verify(horse).move();
 }
 @Test
 public void testGetWinner() {
  // Создаем список из 3-х лошадей
  List<Horse> horseList = createMockHorseList(3);
  // Создаем экземпляр класса Hippodrome с переданным списком лошадей
  Hippodrome hippodrome = new Hippodrome(horseList);
  // Устанавливаем разные значения distance для каждой лошади
  when(horseList.get(0).getDistance()).thenReturn(100.0);
  when(horseList.get(1).getDistance()).thenReturn(200.0);
  when(horseList.get(2).getDistance()).thenReturn(150.0);
  // Получаем победителя с помощью метода getWinner()
  Horse winner = hippodrome.getWinner();
  // Проверяем, что возвращаемая лошадь является ожидаемым победителем
  assertEquals(horseList.get(1), winner);
 }

 // -- Вспомогательные методы --

 // -- Метод для создания списка из 30 разных лошадей с 30-ю разными именами --
 private List<Horse> createHorseListWithDifferentNames() {
  List<Horse> horseList = new ArrayList<>();
  for (int i = 1; i <= 30; i++) {
   String horseName = "Horse " + i;
   horseList.add(new Horse(horseName, 10,10));
  }
  return horseList;
 }

 // -- Метод для создания списка из count лошадей (моков) --
 private List<Horse> createMockHorseList(int count) {
  List<Horse> horseList = new ArrayList<>();
  for (int i = 0; i < count; i++) {
   Horse horse = mock(Horse.class);
   horseList.add(horse);
  }
  return horseList;
 }

}
