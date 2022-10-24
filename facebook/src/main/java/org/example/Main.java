package org.example;

import org.example.model.User;
import org.example.service.UserService;

import java.io.File;
import java.util.Scanner;

public class Main {
    static UserService userService=new UserService();
    static Scanner scannerStr=new Scanner(System.in);
    static Scanner scannerInt=new Scanner(System.in);
    public static void main(String[] args) {
        int var1=10;
        while (var1!=0){
            System.out.println("1.LOG IN 2.REGISTRATION 0.EXIT");
            var1=scannerInt.nextInt();
            switch (var1){
                case 1->{
                    System.out.println("PHONE_NUMBER: ");
                    String phoneNumber=scannerStr.nextLine();

                    System.out.println("PASSWORD: ");
                    String password=scannerStr.nextLine();

                    User currentUser=userService.logIn(password,phoneNumber);
                    if (currentUser!=null){
                        int var2=10;
                        while (var2!=0){
                            System.out.println("1.MY_ACCOUNT 2.MESSENGER 3.FRIENDS 0.BACK");
                            var2=scannerInt.nextInt();

                        }
                    }

                }
                case 2->{
                    System.out.println("FIRST NAME: ");
                    String firstName=scannerStr.nextLine();

                    System.out.println("LAST NAME: ");
                    String lastName=scannerStr.nextLine();

                    System.out.println("PHONE_NUMBER:");
                    String phoneNumber=scannerStr.nextLine();

                    System.out.println("PASSWORD: ");
                    String password=scannerStr.nextLine();

                    System.out.println("DATE_OF_BIRTH: ");
                    int date=scannerInt.nextInt();

                    System.out.println("YEAR_OF_BIRTH: ");
                    int year=scannerInt.nextInt();

                    System.out.println("MONTH_OF_BIRTH:");
                    String month=scannerStr.nextLine();

                    System.out.println("GANDER: 1.MALE 2.FEMALE ");
                    String gander=null;
                    var1=scannerInt.nextInt();
                    switch (var1){
                        case 1->{
                            gander="male";
                        }
                        case 2->{
                            gander="female";
                        }
                    }
                    System.out.println(userService.createUser(firstName, lastName, phoneNumber, password, month, date, year, gander));
                    File file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber));
                    file.mkdir();
                    file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber)+"\\chats");
                    file.mkdir();
                    file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber)+"\\groups");
                    file.mkdir();
                    file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber)+"\\posts");
                    file.mkdir();
                    file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber)+"\\comments");
                    file.mkdir();
                    file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber)+"\\friends");
                    file.mkdir();
                    file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+userService.getIdByPhoneNumber(phoneNumber)+"\\likes");
                    file.mkdir();
                }
                case 0->{}
            }
        }
    }
}