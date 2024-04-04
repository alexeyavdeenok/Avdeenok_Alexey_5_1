import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  private static final Scanner input = new Scanner(System.in);
  private static final ArrayList<Person> list = new ArrayList<>();
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {

    while (true) {
      System.out.println("Меню:");
      System.out.println("1. Создать пустой объект");
      System.out.println("2. Создать объект используя ручной ввод");
      System.out.println("3. Редактировать объект по индексу");
      System.out.println("4. Вывод в консоль информации о всех объектах");
      System.out.println("5. Выполнение алгоритма и вывод результата");
      System.out.println("6. Сортировка объектов по выбранному полю");
      System.out.println("7. Завершение работы программы");

      int choice = 0;
      if (input.hasNextInt()) {
        choice = input.nextInt();
      } else {
        input.next();
      }
      switch (choice) {
        case 1:
          Person human = new Person();
          list.add(human);
          System.out.println("Пустой объект добавлен");
          break;
        case 2:
          createObject();
          break;
        case 3:
          try {
            emptyListCheck();
          if (changeObject()) {
            System.out.println("Вы завершили изменение");
            break;
          } else {
            System.out.println("Неверный индекс");
            break;
          }
          } catch (EmptyListException e){//Простой перехват исключения и логирование
            LOGGER.log(Level.SEVERE, "Пустой список: " + e.getMessage(), e);
            break;
          }
        case 4:
          try {
            emptyListCheck();
            printObject();
            break;
          } catch (EmptyListException e){
            LOGGER.log(Level.WARNING, "Пустой список: " + e.getMessage(), e);
            break;
          }
        case 5:
          try {
            emptyListCheck();
          if (doAlgoritm()) {
            break;
          } else {
            System.out.println("Неверный индекс");
            break;
            }
          } catch (EmptyListException e) {
            LOGGER.log(Level.SEVERE, "Пустой список: " + e.getMessage(), e);
            break;
          }
        case 6:
          try {
            emptyListCheck();
            sortList();
            break;
          } catch (EmptyListException e) {
            LOGGER.log(Level.SEVERE, "Пустой список: " + e.getMessage(), e);
            break;
          }
        case 7:
          System.out.println("Программа завершена");
          System.exit(0);
          break;
        default:
          System.out.println("Ошибка ввода");
      }
    }
  }

  private static void createObject() {
    System.out.println("Введите имя: ");
    String name = input.next();
    System.out.println("Выберите пол (мужской / женский)");
    String gender = input.next();

    try {
    int age;
    double height;
    double weight;

    System.out.println("Введите возраст:");
    age = input.nextInt();

    System.out.println("Введите рост в сантиметрах:");
    height = input.nextDouble();

    System.out.println("Введите вес в килограммах:");
    weight = input.nextDouble();

    try {
    Person person = new Person(name, gender, age, height, weight);
    list.add(person);
    System.out.println("Объект добавлен");
    } catch (FalseInputException e) {
      System.out.println(e.getMessage());
    }
    } catch (Exception e) {
      input.next();
      System.out.println("Ошибка ввода");
    }
  }

  private static void printObject() {
    for (Person i : list) {
      System.out.println("Индекс объекта: " + list.indexOf(i));
      System.out.println(i);
    }
  }

  private static boolean changeObject() {
    printObject();
    System.out.println("Введите индекс объекта для изменения полей: ");
    int index;
    if (input.hasNextInt()) {
      index = input.nextInt();
    } else {
      input.next();
      return false;
    }
    if (index < 0 || index >= list.size()) {
      return false;
    }
    Person person = list.get(index);
    while (true) {
      System.out.println("1. Изменить имя");
      System.out.println("2. Изменить гендер");
      System.out.println("3. Изменить возраст");
      System.out.println("4. Изменить рост");
      System.out.println("5. Изменить вес");
      System.out.println("6. Завершить изменение объекта");
      int choice = 0;
      if (input.hasNextInt()) {
        choice = input.nextInt();
      } else {
        input.next();
      }
      try {
      switch (choice) {
        case 1:
          System.out.println("Введите имя:");
          String name = input.next();
          person.setName(name);
          break;
        case 2:
          System.out.println("Введите гендер:");
          String gender = input.next();
          person.setGender(gender);
          person.setRetiree();
          break;
        case 3:
          System.out.println("Введите возраст:");
          int age = input.nextInt();
          person.setAge(age);
          person.setRetiree();
          break;
        case 4:
          System.out.println("Введите рост:");
          double height = input.nextDouble();
          person.setHeight(height);
          break;
        case 5:
          System.out.println("Введите вес:");
          double weight = input.nextDouble();
          person.setWeight(weight);
          break;
        case 6:
          return true;
        default:
          System.out.println("Ошибка ввода");
      }
      } catch (FalseInputException e) {
        System.out.println(e.getMessage());
      } catch (Exception e) {
        input.next();
        System.out.println("Ошибка ввода");
      }
    }
  }
  private static void emptyListCheck() throws EmptyListException{
    if (list.isEmpty()) {
      throw new EmptyListException("Объекты не созданы");
    }
  }

  private static boolean doAlgoritm() {
    printObject();
    System.out.println("Введите индекс объекта для выполнения алгоритма: ");
    int index;
    if (input.hasNextInt()) {
      index = input.nextInt();
    } else {
      input.next();
      return false;
    }
    if (index < 0 || index >= list.size()) {
      return false;
    }
    Person person = list.get(index);
    System.out.println("Телосложение исходя из индекса массы тела: " + person.indexMas());
    return true;
  }

  private static void sortList() {
    System.out.println("Выберите поле для сортировки:");
    System.out.println("1. Имя");
    System.out.println("2. Гендер");
    System.out.println("3. Возраст");
    System.out.println("4. Рост");
    System.out.println("5. Вес");
    System.out.println("6. Пенсионер");

    int choice = 0;
    if (input.hasNextInt()) {
      choice = input.nextInt();
    } else {
      input.next();
    }

    Comparator<Person> comparator = null;
    String fieldName = null;
    switch (choice) {
      case 1:
        comparator = Comparator.comparing(Person::getName);
        fieldName = "Имя";
        break;
      case 2:
        comparator = Comparator.comparing(Person::getGender);
        fieldName = "Гендер";
        break;
      case 3:
        comparator = Comparator.comparingInt(Person::getAge);
        fieldName = "Возраст";
        break;
      case 4:
        comparator = Comparator.comparingDouble(Person::getHeight);
        fieldName = "Рост";
        break;
      case 5:
        comparator = Comparator.comparingDouble(Person::getWeight);
        fieldName = "Вес";
        break;
      case 6:
        comparator = Comparator.comparing(Person::getRetire);
        fieldName = "Пенсионер";
        break;
      default:
        System.out.println("Ошибка ввода");
        return;
    }

    if (comparator != null) {
      Collections.sort(list, comparator);
      System.out.println("Список отсортирован по полю \"" + fieldName + "\"");
    }
  }
}


