import java.util.Objects;

public class Person {
  private String name;
  private String gender;
  private int age = -1;
  private double height;
  private double weight;
  private boolean retiree;

  public Person() {
    try {
    setName("None");
    setGender("None");
    setAge(0);
    setHeight(0);
    setWeight(0);
    setRetiree();
    } catch (FalseInputException e){ //Подавление исключения
    }
  }

  public Person(String name, String gender, int age, double height, double weight) throws FalseInputException{
    try{
    setName(name);
    setGender(gender);
    setAge(age);
    setHeight(height);
    setWeight(weight);
    setRetiree();
    } catch (FalseInputException e) {
      System.out.println(e.getMessage());
      throw new FalseInputException("Объект не создан"); //Повторное генерирование исключения
    }
  }

  @Override
  public String toString() {
    return "Имя: "
        + this.name
        + "\nПол: "
        + this.gender
        + "\nВозраст: "
        + this.age
        + "\nРост: "
        + this.height * 100
        + "\nВес: "
        + this.weight
        + "\nПенсионер: " + this.retiree + "\n";
  }
  public void setRetiree() {
    if (Objects.equals(this.gender, "мужской") && this.age > 62) {
      this.retiree = true;
    }
    else this.retiree = Objects.equals(this.gender, "женский") && this.age > 57;
    }
  public boolean getRetire() {
    return this.retiree;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) throws FalseInputException{
    if (name.isEmpty()) {
      throw new FalseInputException("Имя не введено");
    } else {
      this.name = name;
    }
  }

  public String getGender() {
    return this.gender;
  }

  public void setGender(String gender) throws FalseInputException{
    String gendercheck = gender.toLowerCase();
    switch (gendercheck) {
      case "мужской":
        this.gender = gender;
        break;
      case "женский":
        this.gender = gender;
        break;
      case "none":
        this.gender = gender;
        break;
      default:
        throw new FalseInputException("Введен недопустимый пол");
    }
  }

  public int getAge() {
    return this.age * 100;
  }

  public void setAge(int age) throws FalseInputException{
    try {
    assert age >= 0 && age < 150 : "Неверный возраст"; // Утверждение о недопустимом возрасте
    this.age = age;
    } catch(AssertionError e) {
      throw new FalseInputException(e.getMessage());
    }
  }

  public double getHeight() {
    return this.height * 100;
  }

  public void setHeight(double height) throws FalseInputException{
    if (height >= 36 && height <= 251 || height == 0) {
      this.height = height / 100;
    } else {
      throw new FalseInputException("Введен недопустимый рост");
    }
  }

  public double getWeight() {
    return this.weight;
  }

  public void setWeight(double weight) throws FalseInputException{
    if (weight >= 3.0 && weight <= 300 || weight == 0) {
      this.weight = weight;
    } else {
      throw new FalseInputException("Недопустимый вес");
    }
  }

  public String indexMas() {
    if (this.height == 0) {
      return "Индекс массы тела вычислить нельзя";
    }
    double masIndex = this.weight / (this.height * this.height);
    if (masIndex < 16.5) {
      return "Большой недовес";
    } else if (masIndex >= 16.5 && masIndex < 18.5) {
      return "Недовес";
    } else if (masIndex >= 18.5 && masIndex < 25.0) {
      return "Нормальный вес";
    } else if (masIndex >= 25.0 && masIndex < 30.0) {
      return "Избыточный вес";
    } else if (masIndex >= 30 && masIndex < 35) {
      return "Ожирение";
    } else if (masIndex >= 35 && masIndex < 40) {
      return "Сильное ожирение";
    } else {
      return "Супер ожирение";
    }
  }
}
