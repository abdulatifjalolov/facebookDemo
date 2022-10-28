package org.example.dto;

import org.example.model.User;
import org.example.service.UserService;

import java.util.Scanner;

public class UserDto {
    public static User registration(Scanner scanner, Scanner scannerInt, User user) {
        System.out.println("FIRST NAME: ");
        String firstName = scanner.nextLine();
        user.setFirstName(firstName);

        System.out.println("LAST NAME: ");
        String lastName = scanner.nextLine();
        user.setLastName(lastName);

        System.out.println("PHONE_NUMBER:");
        String phoneNumber = scanner.nextLine();
        user.setPhoneNumber(phoneNumber);

        System.out.println("PASSWORD: ");
        String password = scanner.nextLine();
        user.setPassword(password);

        System.out.println("DATE_OF_BIRTH: ");
        int date = scannerInt.nextInt();
        user.setBirthday(date);

        System.out.println("YEAR_OF_BIRTH: ");
        int year = scannerInt.nextInt();
        user.setYear(year);

        System.out.println("MONTH_OF_BIRTH:");
        String month = scanner.nextLine();
        user.setMonth(month);

        System.out.println("GANDER: 1.MALE 2.FEMALE ");
        String gander = null;
        int var1 = scannerInt.nextInt();
        switch (var1) {
            case 1 -> {
                gander = "male";
            }
            case 2 -> {
                gander = "female";
            }
        }
        user.setGander(gander);

        return user;
    }
}
