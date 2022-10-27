package org.example;

import org.example.dto.UserDto;
import org.example.model.Post;
import org.example.model.User;
import org.example.service.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String headUrl = "C:\\Users\\abdulatif\\forJAVA\\facebook\\allUsers";
    static UserService userService = new UserService();
    static ChatService chatService = new ChatService();
    static GroupService groupService = new GroupService();
    static PostService postService = new PostService();
    static Scanner scannerStr = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int var1 = 10;
        while (var1 != 0) {
            System.out.println("1.LOG IN 2.REGISTRATION 0.EXIT");
            var1 = scannerInt.nextInt();
            switch (var1) {
                case 1 -> {
                    User currentUser = userService.logIn(headUrl);
                    if (currentUser != null) {
                        int var = 10;
                        while (var != 0) {
                            System.out.println("1.MY_ACCOUNT 2.MESSENGER 3.FRIENDS 0.BACK");
                            var = scannerInt.nextInt();
                            switch (var) {
                                case 1 -> {
                                    forMyAccount(currentUser);
                                }
                                case 2 -> {
                                    forMessenger(currentUser);
                                }
                                case 3 -> {
                                    forFriends(currentUser);
                                }
                                case 0 -> {
                                }
                            }
                        }

                    }

                }
                case 2 -> {
                    User user = new User();
                    User currentUser = UserDto.registration(new Scanner(System.in), new Scanner(System.in), user);
                    if (!userService.isHasUser(currentUser)) {
                        System.out.println("THIS NUMBER ALREADY REGISTERED");
                        break;
                    }
                    FileUtils.createFileForUser(currentUser, headUrl);
                    FileUtils.createChildFiles(headUrl, user.getPhoneNumber());
                    userService.addUsersInfoInGson(currentUser, headUrl);
                }
                case 0 -> {
                }
            }
        }
    }

    private static void forMyAccount(User currentUser) throws IOException {
        int var = 10;
        while (var != 0) {
            System.out.println("1.POSTS 2.EDIT_PROFILE 0.BACK");
            var = scannerInt.nextInt();
            switch (var) {
                case 1 -> {
                    forPosts(currentUser);
                }
                case 2 -> {
                    forEditProfile(currentUser, headUrl);
                }
                case 0 -> {
                }
            }
        }
    }

    private static void forEditProfile(User currentUser, String headUrl) throws IOException {
        User user = new User();
        User editUser = UserDto.registration(new Scanner(System.in), new Scanner(System.in), user);
        userService.addUsersInfoInGson(editUser, headUrl);
        for (User friend : userService.getAllFriends(headUrl, currentUser)) {
            userService.editUserInOtherUsersFile(editUser, headUrl, friend.getPhoneNumber());
        }
        System.out.println("SUCCESSFULLY EDITED");
    }

    private static void forPosts(User currentUser) throws IOException {
        int var = 10;
        while (var != 0) {
            System.out.println("1.CREATE_NEW_POST 2.MY_POSTS 0.BACK");
            var = scannerInt.nextInt();
            switch (var) {
                case 1 -> {
                    Post newPost = postService.createNewPost(currentUser);
                    postService.writePostInFile(currentUser, headUrl, newPost);
                }
                case 2 -> {
                    for (Post post : postService.getAllPosts(headUrl, currentUser.getPhoneNumber())) {
                        System.out.println(post);
                    }
                }
                case 0 -> {
                }
            }
        }
    }

    private static void forMessenger(User currentUser) throws IOException {
        int var = 10;
        while (var != 0) {
            System.out.println("1.PERSONAL_CHATS 2.GROUPS 0.BACK");
            var = scannerInt.nextInt();
            switch (var) {
                case 1 -> {
                    for (User friend : userService.getAllFriends(headUrl, currentUser)) {
                        System.out.println(friend);
                    }
                    int var1 = 10;
                    while (var1 != 0) {
                        System.out.println("ENTER_PHONE_NUMBER: (0 => BACK)");
                        var1 = scannerInt.nextInt();
                        if (var1 != 0) {
                            String phoneNumber = String.valueOf(var1);
                            chatService.createChatFile(currentUser, phoneNumber, headUrl);

                            String s = chatService.getPersonalChat(headUrl, currentUser, phoneNumber);
                            if (s != null) {
                                System.out.println(s);
                            }
                            System.out.println("ENTER MESSAGE: (0 => BACK)");
                            if (s == null) {
                                System.out.println("NO MESSAGE HERE YET");
                            }
                            message(currentUser, phoneNumber);
                        }
                    }
                }
                case 2 -> {
                    int var1 = 10;
                    while (var1 != 0) {
                        System.out.println("1.ALL_GROUPS 2.ADD_GROUP 3.ADD_MEMBER_IN_GROUP 0.BACK");
                        var1 = scannerInt.nextInt();
                        switch (var1) {
                            case 1 -> {
                                forAllGroups(headUrl, currentUser);
                            }
                            case 2 -> {
                                forCreateGroup(headUrl, currentUser);
                            }
                            case 3 -> {
                                forAddMember(headUrl, currentUser);
                            }
                            case 0 -> {
                            }
                        }
                    }
                }
                case 0 -> {
                }
            }
        }
    }

    private static void forAddMember(String headUrl, User currentUser) throws IOException {
        System.out.println("FRIENDS LIST: ");
        for (User user : userService.getAllFriends(headUrl, currentUser)) {
            System.out.println(user);
        }

        System.out.println("ENTER GROUP NAME: ");
        String groupName = new Scanner(System.in).nextLine();


        List<Integer> membersList = new ArrayList<>();
        int var = 10;
        while (var != 0) {
            System.out.println("ENTER USER NUMBER: (0.BACK)");
            var = scannerStr.nextInt();
            membersList.add(var);
        }
        if (membersList.size() > 0) {
            membersList.add(Integer.valueOf(currentUser.getPhoneNumber()));
        }
        groupService.createFileForGroup(headUrl, groupName, currentUser, membersList);
    }

    private static void forCreateGroup(String headUrl, User currentUser) throws IOException {
        System.out.println("FRIENDS LIST: ");
        for (User user : userService.getAllFriends(headUrl, currentUser)) {
            System.out.println(user);
        }

        System.out.println("ENTER GROUP NAME: ");
        String groupName = new Scanner(System.in).nextLine();

        List<Integer> membersList = new ArrayList<>();
        int var = 10;
        while (var != 0) {
            System.out.println("ENTER USER NUMBER: (0.BACK)");
            var = scannerStr.nextInt();
            membersList.add(var);
        }
        if (membersList.size() > 0) {
            membersList.add(Integer.valueOf(currentUser.getPhoneNumber()));
        }
        groupService.createFileForGroup(headUrl, groupName, currentUser, membersList);
    }

    private static void forAllGroups(String headUrl, User currentUser) throws IOException {

        while (true) {
            if (groupService.getAllGroupsName(headUrl, currentUser) == null) {
                System.out.println("YOU HAVEN'T GROUP YET");
                break;
            }
            System.out.println("ALL GROUPS: ");
            for (String s : groupService.getAllGroupsName(headUrl, currentUser)) {
                System.out.println(s);
            }


            System.out.println("ENTER GROUP NAME: (0 => BACK)");
            String groupName = new Scanner(System.in).nextLine();
            if (groupName.equals("0")) {
                break;
            }

            if (groupService.getGroupChat(headUrl, groupName, currentUser) != null && !groupService.getGroupChat(headUrl, groupName, currentUser).equals("")) {
                System.out.println(groupService.getGroupChat(headUrl, groupName, currentUser));
            }

            File groupFile = groupService.getGroupFile(headUrl, groupName, currentUser);
            System.out.println("ENTER MESSAGE: (0 => BACK)");
            messageForGroup(currentUser, headUrl, groupFile);
        }

    }

    private static void messageForGroup(User currentUser, String headUrl, File groupFile) throws IOException {
        int var = 10;
        while (var != 0) {
            String message = scannerStr.nextLine();
            if (message.equals("0")) {
                var = Integer.parseInt(message);
            }
            if (var != 0 && !message.equals("")) {
                message = currentUser.getFirstName() + " " + currentUser.getLastName() + " : " + message + " [" + chatService.getCurrentTime() + "]\n";
                for (User user : userService.getAllFriends(headUrl, currentUser)) {
                    File file1 = new File(headUrl + "\\" + user.getPhoneNumber() + "\\groups");
                    for (File file : file1.listFiles()) {
                        if (groupFile.getName().equals(file.getName())) {
                            groupService.writeMessageToFiles(file, message);
                        }
                    }
                }
                File file = new File(headUrl + "\\" + currentUser.getPhoneNumber() + "\\groups\\" + groupFile.getName());
                groupService.writeMessageToFiles(file, message);
            }
        }
    }


    private static void message(User currentUser, String phoneNumber) throws IOException {
        int var = 10;
        while (var != 0) {
            String message = scannerStr.nextLine();
            if (message.equals("0")) {
                var = Integer.parseInt(message);
            }
            if (var != 0 && !message.equals("")) {
                File personalCHat1 = chatService.getPersonalCHats(headUrl, currentUser, phoneNumber).get(0);
                message = currentUser.getFirstName() + " " + currentUser.getLastName() + " : " + message + " [" + chatService.getCurrentTime() + "]";
                chatService.writeMessagetoPersonalChat(personalCHat1, message);

                File personalCHat2 = chatService.getPersonalCHats(headUrl, currentUser, phoneNumber).get(1);
                chatService.writeMessagetoPersonalChat(personalCHat2, message);
            }
        }
    }

    private static void forFriends(User currentUser) throws IOException {
        int var2 = 10;
        while (var2 != 0) {
            System.out.println("1.FRIENDS_LIST 2.FRIEND_REQUESTS 3.BIRTHDAYS 4.ADD_NEW_FRIEND 5.FRIENDS_POSTS 6.REMOVE_FRIEND 0.BACK");
            var2 = scannerInt.nextInt();
            switch (var2) {
                case 1 -> {
                    for (User friend : userService.getAllFriends(headUrl, currentUser)) {
                        System.out.println(friend.toString());
                    }
                }
                case 2 -> {
                    for (User user : userService.getAllRequests(headUrl, currentUser)) {
                        if (!userService.isFriend(headUrl, currentUser, user.getPhoneNumber())) {
                            System.out.println(user);
                        }
                    }
                    int var4 = 10;
                    while (var4 != 0) {
                        System.out.println("ENTER_PHONE_NUMBER: (0 => BACK)");
                        var4 = scannerStr.nextInt();
                        String friendNumber = String.valueOf(var4);
                        if (var4 != 0) {
                            userService.addFriend(headUrl, currentUser, friendNumber);
                        }
                    }

                }
                case 3 -> {
                    for (String birthday : userService.getAllBirthdays(headUrl, currentUser)) {
                        System.out.println(birthday);
                    }
                }
                case 4 -> {
                    for (User user : userService.getAllUsers(headUrl)) {
                        if (!userService.isFriend(headUrl, currentUser, user.getPhoneNumber())) {
                            if (!currentUser.getPhoneNumber().equals(user.getPhoneNumber())) {
                                System.out.println(user);
                            }
                        }
                    }
                    int var5 = 10;
                    while (var5 != 0) {
                        System.out.println("ENTER_PHONE_NUMBER: (0 => BACK)");
                        var5 = scannerStr.nextInt();
                        String friendNumber = String.valueOf(var5);
                        if (var5 != 0) {
                            userService.addFriendInRequests(headUrl, currentUser, friendNumber);
                        }
                    }
                }
                case 5 -> {
                    for (User friend : userService.getAllFriends(headUrl, currentUser)) {
                        System.out.println(friend);
                    }

                    while (true) {
                        System.out.println("ENTER PHONE NUMBER: (0 => BACK)");
                        String phoneNumber = scannerStr.nextLine();
                        if (phoneNumber.equals("0")) {
                            break;
                        }

                        for (Post post : postService.getAllPosts(headUrl, phoneNumber)) {
                            System.out.println(post);
                        }
                    }
                }
                case 6 -> {
                    for (User friend : userService.getAllFriends(headUrl, currentUser)) {
                        System.out.println(friend);
                    }

                    System.out.println("ENTER PHONE NUMBER: ");
                    String phoneNumber = scannerStr.nextLine();

                    userService.removeFriend(headUrl, currentUser, phoneNumber);
                }
                case 0 -> {
                }
            }
        }
    }
}