package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      // Создаем машины
      Car car1 = new Car("Model1", 111);
      Car car2 = new Car("Model2", 222);
      Car car3 = new Car("Model3", 333);
      Car car4 = new Car("Model4", 444);

      // Создаем пользователей с машинами
      User user1 = new User("User1", "Lastname1", (byte) 25);
      user1.setCar(car1);
      userService.add(user1);

      User user2 = new User("User2", "Lastname2", (byte) 30);
      user2.setCar(car2);
      userService.add(user2);

      User user3 = new User("User3", "Lastname3", (byte) 35);
      user3.setCar(car3);
      userService.add(user3);

      User user4 = new User("User4", "Lastname4", (byte) 40);
      user4.setCar(car4);
      userService.add(user4);

      // Получаем всех пользователей
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Age = " + user.getAge());
         System.out.println("Car = " + user.getCar().getModel() +
                 " (series " + user.getCar().getSeries() + ")");
         System.out.println();
      }

      // Проверяем поиск пользователя по машине
      User foundUser = userService.findUserByCarModelAndSeries("Model2", 222);
      System.out.println("Найден пользователь по машине:");
      System.out.println(foundUser);

      context.close();
   }
}