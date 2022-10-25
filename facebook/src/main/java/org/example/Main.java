package org.example;

import org.example.model.User;
import org.example.service.UserService;

import java.io.*;
import java.util.Scanner;

public class Main {
    static UserService userService = new UserService();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args){
        int var1 = 10;
        while (var1 != 0) {
            System.out.println("1.LOG IN 2.REGISTRATION 0.EXIT");
            var1 = scannerInt.nextInt();
            switch (var1) {
                case 1 -> {
                    System.out.println("PHONE_NUMBER: ");
                    String phoneNumber = scannerStr.nextLine();

                    System.out.println("PASSWORD: ");
                    String password = scannerStr.nextLine();

                    User currentUser = userService.logIn(password, phoneNumber);
                    if (currentUser != null) {
                        int var3 = 10;
                        while (var3 != 0) {
                            System.out.println("1.MY_ACCOUNT 2.MESSENGER 3.FRIENDS 0.BACK");
                            var3 = scannerInt.nextInt();
                            switch (var3) {
                                case 1 -> {
                                    System.out.println("1.POSTS 2.EDIT_PROFILE 3.BLOCKED_ACCOUNTS 4.LOG_OUT 0.BACK");
                                }
                                case 2 -> {
                                    System.out.println("1.PERSONAL_CHATS 2.GROUPS 3.FRIENDS_LIST 0.BACK");
                                }
                                case 3 -> {
                                    System.out.println("1.FRIENDS_LIST 2.FRIEND_REQUEST 3.BIRTHDAYS 4.FIND_FRIENDS 0.BACK");
                                }
                                case 0 -> {
                                }
                            }
                        }

                    }

                }
                case 2 -> {
                    System.out.println("FIRST NAME: ");
                    String firstName = scannerStr.nextLine();

                    System.out.println("LAST NAME: ");
                    String lastName = scannerStr.nextLine();

                    System.out.println("PHONE_NUMBER:");
                    String phoneNumber = scannerStr.nextLine();

                    System.out.println("PASSWORD: ");
                    String password = scannerStr.nextLine();

                    System.out.println("DATE_OF_BIRTH: ");
                    int date = scannerInt.nextInt();

                    System.out.println("YEAR_OF_BIRTH: ");
                    int year = scannerInt.nextInt();

                    System.out.println("MONTH_OF_BIRTH:");
                    String month = scannerStr.nextLine();

                    System.out.println("GANDER: 1.MALE 2.FEMALE ");
                    String gander = null;
                    var1 = scannerInt.nextInt();
                    switch (var1) {
                        case 1 -> {
                            gander = "male";
                        }
                        case 2 -> {
                            gander = "female";
                        }
                    }
                    boolean created = userService.createUser(firstName, lastName, phoneNumber, password, month, date, year, gander);
                    try {
                        userService.addUserInFiles(phoneNumber,created);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 0 -> {
                }
            }
        }
    }
}