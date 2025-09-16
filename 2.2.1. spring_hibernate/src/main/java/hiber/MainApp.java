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

      User user1 = new User("John", "Doe", "john@example.com", new Car("Toyota", 123));
      User user2 = new User("Jane", "Smith", "jane@example.com", new Car("BMW", 456));
      User user3 = new User("Mike", "Brown", "mike@example.com", new Car("Audi", 789));
      User user4 = new User("Anna", "Taylor", "anna@example.com", new Car("Mercedes", 321));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      System.out.println("\n--- Все пользователи из базы данных: ---");
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      System.out.println("\n--- Поиск пользователя по машине BMW 456 ---");
      User foundUser = userService.getUserByCar("BMW", 456);
      System.out.println("Найден пользователь: " + foundUser);

      context.close();
   }
}