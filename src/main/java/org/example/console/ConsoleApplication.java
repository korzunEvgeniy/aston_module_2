package org.example.console;

import org.example.console.model.UserDto;
import org.example.service.UserServiceImpl;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleApplication {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleApplication.class);
    private static final UserService userService = new UserServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        while (true) {
            printMenu();
            System.out.println("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createUser();
                    break;
                case "2":
                    findUserByEmail();
                    break;
                case "3":
                    findAllUsers();
                    break;
                case "4":
                    updateUser();
                    break;
                case "5":
                    deleteUser();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Wrong command! Try again.");
            }
        }
    }

    private static void createUser() {
        System.out.println("Input mame: ");
        String name = scanner.nextLine();
        System.out.println("Input email: ");
        String email = scanner.nextLine();
        System.out.println("Input age: ");
        int age = Integer.parseInt(scanner.nextLine());

        UserDto userDto = new UserDto(name, email, age);
        userService.create(userDto);
        System.out.println("User created successfully!");
        logger.debug("User created: {}", userDto.getName());
    }

    private static void findUserByEmail() {
        System.out.println("Input email: ");
        String email = scanner.nextLine();
        System.out.println(userService.findByEmail(email));
        logger.debug("User found: {}", email);
    }

    private static void findAllUsers() {
        if (userService.findAll().isEmpty()) {
            System.out.println("List of users is empty");
            logger.debug("List of users is empty");
        } else {
            System.out.println(userService.findAll());
            logger.debug("List of users got");

        }
    }

    private static void updateUser() {
        UserDto updatedUser = null;

        System.out.println("Input user`s email for updating: ");
        String email = scanner.nextLine();

        if (userService.findByEmail(email) != null) {
            System.out.println("Input new name or skip: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) updatedUser.setName(name);

            System.out.println("Input new email or skip: ");
            String emailNew = scanner.nextLine();
            if (email != emailNew) updatedUser.setEmail(emailNew);

            System.out.println("Input new age or skip: ");
            String ageNew = scanner.nextLine();
            if (!ageNew.isEmpty()) updatedUser.setAge(Integer.parseInt(ageNew));

            System.out.println("User updated successfully!");
            logger.debug("User with email {} updated", email);
        } else {
            System.out.println("This email is not found!");
            logger.debug("User with email {} not found", email);
        }
    }

    private static void deleteUser() {
        System.out.println("Input user`s email for deleting: ");
        String email = scanner.nextLine();
        userService.delete(email);
        System.out.println("Attempt to delete user!");
        logger.debug("Attempt to delete user with email {}", email);
    }

    private static void printMenu() {
        System.out.println("User Service Menu: ");
        System.out.println("1. Create user");
        System.out.println("2. Get user by email");
        System.out.println("3. Get all users");
        System.out.println("4. Update user");
        System.out.println("5. Delete user");
        System.out.println("6. Exit");
    }
}
