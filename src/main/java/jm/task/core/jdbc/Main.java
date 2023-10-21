package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        System.out.println("Table created!");

        userService.saveUser("Alex", "Ivanov", (byte) 25);
        System.out.println("saved data");
        userService.saveUser("Pavel", "Petrov", (byte) 22);
        System.out.println("saved data");
        userService.saveUser("Anton", "Sidorov", (byte) 30);
        System.out.println("saved data");
        userService.saveUser("Igor", "Sidorov", (byte) 20);
        System.out.println("saved data");
        userService.saveUser("Maria", "Dmitrieva", (byte) 19);
        System.out.println("saved data");
        System.out.println("All users:");

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        System.out.println("Table cleaned!");

        userService.dropUsersTable();
        System.out.println("Table dropped!");


    }
}
