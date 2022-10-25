package org.example;

import org.example.dto.UserDto;
import org.example.model.User;
import org.example.service.FileUtils;
import org.example.service.UserService;

import java.io.*;
import java.util.Scanner;

public class Main {
    static String headUrl="C:\\Users\\abdulatif\\forJAVA\\facebook\\allUsers";
    static UserService userService = new UserService();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
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

                    User currentUser =userService.logIn(password,phoneNumber,headUrl);
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
                    User user= new User();
                    User currentUser=UserDto.registration(new Scanner(System.in),new Scanner(System.in),user);
                    if (!userService.isHasUser(currentUser)){
                        System.out.println("THIS NUMBER ALREADY REGISTERED");
                        break;
                    }
                    FileUtils.createFileForUser(currentUser,headUrl);
                    FileUtils.createChildFiles(headUrl,user.getPhoneNumber());
                    userService.addUsersInfoInGson(currentUser,headUrl);
                }
                case 0 -> {
                }
            }
        }
    }
}