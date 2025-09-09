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
      Car car1 = new Car("BMW", 3);
      Car car2 = new Car("Audi", 5);

      // Создаем пользователей с машинами
      User user1 = new User("Alice", "Smith", "alice@mail.com", car1);
      User user2 = new User("Bob", "Johnson", "bob@mail.com", car2);

      // Сохраняем в БД
      userService.add(user1);
      userService.add(user2);

      // Получаем всех пользователей
      List<User> users = userService.listUsers();
      for (User u : users) {
         System.out.println("Id = " + u.getId() +
                 ", Name = " + u.getFirstName() +
                 ", Car = " + u.getCar().getModel() +
                 ", Series = " + u.getCar().getSeries());
      }

      // Ищем пользователя по машине
      User owner = userService.getUserByCar("BMW", 3);
      System.out.println("Владелец BMW 3: " + owner.getFirstName());

      context.close();
   }
}