import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HippodromeTest {
 @Test
 protected void testConstructorAndMethods1() {
  assertThrows(IllegalArgumentException.class, () ->
  {  new Hippodrome(null) ;  });
 }

 @Test
 protected void testConstructorAndMethods2() {
  Throwable exception = assertThrows(IllegalArgumentException.class, () ->
  {  new Hippodrome(null) ;  });
  assertEquals("Horses cannot be null.", exception.getMessage());
 }

 @Test
 protected void testConstructorAndMethods3() {
  assertThrows (IllegalArgumentException.class, () ->
  {  new Hippodrome(new ArrayList<Horse>()); });

 }

 @Test
 protected void testConstructorAndMethods4() {
  Throwable exception = assertThrows(IllegalArgumentException.class, () ->
  {  new Hippodrome(new ArrayList<Horse>()); });
  assertEquals("Horses cannot be empty.", exception.getMessage());
 }

 // -- Тест методов --

 @Test
 public void testGetHorses() {
  // Создаем список из 30 разных лошадей
  List<Horse> horseList = createHorseList();

  // Создаем экземпляр класса Hippodrome с переданным списком лошадей
  Hippodrome hippodrome = new Hippodrome(horseList);

  // Получаем список лошадей с помощью метода getHorses()
  List<Horse> returnedHorses = hippodrome.getHorses();

  // Проверяем, что полученный список содержит те же объекты и в той же последовательности, что и исходный список
  assertEquals(horseList, returnedHorses);
 }

 // Вспомогательный метод для создания списка из 30 разных лошадей
 private List<Horse> createHorseList() {
  List<Horse> horseList = new ArrayList<>();
  for (int i = 1; i <= 30; i++) {
   String horseName = "Horse " + i;
   horseList.add(new Horse(horseName, 10,10));
  }
  return horseList;
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

 // Вспомогательный метод для создания списка из count моков лошадей
 private List<Horse> createMockHorseList(int count) {
  List<Horse> horseList = new ArrayList<>();
  for (int i = 0; i < count; i++) {
   Horse horse = mock(Horse.class);
   horseList.add(horse);
  }
  return horseList;
 }

 @Test
 public void testGetWinner() {
  List<Horse> horseList = createMockHorseList();
  // Создаем экземпляр класса Hippodrome с переданным списком моков лошадей
  Hippodrome hippodrome = new Hippodrome(horseList);
  // Устанавливаем разные значения distance для каждого мока лошади
  when(horseList.get(0).getDistance()).thenReturn(100.0);
  when(horseList.get(1).getDistance()).thenReturn(200.0);
  when(horseList.get(2).getDistance()).thenReturn(150.0);
  // Получаем победителя с помощью метода getWinner()
  Horse winner = hippodrome.getWinner();
  // Проверяем, что возвращаемая лошадь является ожидаемым победителем
  assertEquals(horseList.get(1), winner);
 }

 // Вспомогательный метод для создания списка из 3 моков лошадей
 private List<Horse> createMockHorseList() {
  List<Horse> horseList = new ArrayList<>();
  for (int i = 1; i <= 3; i++) {
   Horse horse = mock(Horse.class);
   horseList.add(horse);
  }
  return horseList;
 }



}
