package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("TheNaceCar", 1347);
      user1.setUserCar(car1);
      userService.add(user1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 = new Car("TheOldCar", 7777);
      user2.setUserCar(car2);
      userService.add(user2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car3 = new Car("TheBigCar", 4713);
      user3.setUserCar(car3);
      userService.add(user3);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car car4 = new Car("TheBigCar", 1234);
      user4.setUserCar(car4);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car_" +user.getUserCar());
         System.out.println("==========================================");
      }

      System.out.println(userService.getUserByCar("TheNaceCar",1347));

      context.close();
   }
}

//Задание:
//        1. Создайте соединение к своей базе данных и схему. Запустите приложение.
//        Проверьте, что оно полностью работает.  +
//        2. Создайте сущность Car с полями String model и int series,
//        на которую будет ссылаться User с помощью связи one-to-one.
//        3. Добавьте этот класс в настройки hibernate.
//        4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.
//        5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера,
//        владеющего машиной по ее модели и серии.
