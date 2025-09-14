package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("John", "Doe", "john@example.com", new Car("Toyota", 123));
      User user2 = new User("Jane", "Smith", "jane@example.com", new Car("BMW", 456));

      userService.add(user1);
      userService.add(user2);

      User foundUser = userService.getUserByCar("BMW", 456);
      System.out.println("Найден пользователь по машине BMW 456: " + foundUser);

      context.close();
   }
}