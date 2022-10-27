package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.User;

import java.io.*;
import java.util.*;

public class UserService{
    public boolean isHasUser(User user) {
        for (String s : usersList()) {
            if (s.equals(user.getPhoneNumber())) {
                return false;
            }
        }
        return true;
    }

    public User logIn(String headUrl) throws IOException {
        System.out.println("PHONE_NUMBER: ");
        String phoneNumber = new Scanner(System.in).nextLine();

        System.out.println("PASSWORD: ");
        String password = new Scanner(System.in).nextLine();

        User user = FileUtils.getObjectByName(headUrl, phoneNumber);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void addUsersInfoInGson(User user, String headUrl) throws IOException {
        if (user != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(headUrl + "\\" + user.getPhoneNumber() + "\\" + user.getPhoneNumber() + ".txt");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String curr = gson.toJson(user);
            byte[] bytes = curr.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }
    }
    public void editUserInOtherUsersFile(User user,String headUrl,String friendNumber) throws IOException {
        if (user != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(headUrl + "\\" + friendNumber + "\\friends\\" + user.getPhoneNumber() + ".txt");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String curr = gson.toJson(user);
            byte[] bytes = curr.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }
    }

    public String[] usersList() {
        String headUrl = "C:\\Users\\abdulatif\\forJAVA\\facebook\\allUsers";
        File file = new File(headUrl);
        return file.list();
    }

    public List<User> getAllUsers(String headUrl) throws IOException {
        List<User> usersList = new ArrayList<>();
        for (String currentUserName : usersList()) {
            usersList.add(FileUtils.getObjectByName(headUrl, currentUserName));
        }
        return usersList;
    }

    public boolean isFriend(String headUrl,User currentUser,String friendNumber) throws IOException {
        for (User friend : getAllFriends(headUrl, currentUser)) {
            if (friendNumber.equals(friend.getPhoneNumber())){
                return true;
            }
        }
        return false;
    }

    public void addFriend(String headUrl, User currentUser, String friendNumber) throws IOException {
        if (isFriend(headUrl,currentUser,friendNumber)){
            System.out.println("THIS USER ALREADY YOUR FRIEND");
            return;
        }
        String friendsAddress = headUrl + "\\" + currentUser.getPhoneNumber() + "\\friends\\" + friendNumber + ".txt";
        File file = FileUtils.createFileForFriend(friendsAddress);

        User friend = FileUtils.getObjectByName(headUrl, friendNumber);

        FileUtils.writeFriendToFriendsList(friend, file);

        String userAddress = headUrl + "\\" + friendNumber + "\\friends\\" + currentUser.getPhoneNumber() + ".txt";
        file = FileUtils.createFileForFriend(userAddress);

        User user = FileUtils.getObjectByName(headUrl, currentUser.getPhoneNumber());

        FileUtils.writeFriendToFriendsList(user, file);
        System.out.println("SUCCESSFULLY ADDED");
    }

    private File createRequestFile(String headUrl,String friendNumber,User currentUser) throws IOException {
        String requestAddress = headUrl + "\\" + friendNumber + "\\requests\\" + currentUser.getPhoneNumber() + ".txt";
        File file = new File(requestAddress);
        file.createNewFile();
        return file;
    }
    public void addFriendInRequests(String headUrl, User currentUser, String friendNumber) throws IOException {
        if (isFriend(headUrl,currentUser,friendNumber)){
            System.out.println("THIS USER ALREADY YOUR FRIEND");
            return;
        }
        File file=createRequestFile(headUrl,friendNumber,currentUser);
        User requestedUser = FileUtils.getObjectByName(headUrl, currentUser.getPhoneNumber());

        FileOutputStream fileOutputStream= new FileOutputStream(file);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String current=gson.toJson(requestedUser);
        byte [] bytes=current.getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        System.out.println("REQUEST SENT");
    }
    public List<User> getAllRequests(String headUrl,User currentUser) throws IOException {
        List<User> users1=new ArrayList<>();
        File file= new File(headUrl+"\\"+currentUser.getPhoneNumber()+"\\requests");
        for (File current : file.listFiles()) {
            FileReader fileReader=new FileReader(current);
            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            users1.add(gson.fromJson(fileReader,User.class));
            fileReader.close();
        }
        return users1;
    }
    public void removeFriend(String headUrl,User user,String removedPhoneNumber){
        File file=new File(headUrl+"\\"+user.getPhoneNumber()+"\\friends\\"+removedPhoneNumber+".txt");
        file.delete();

        file=new File(headUrl+"\\"+user.getPhoneNumber()+"\\personal_chats\\"+removedPhoneNumber+".txt");
        file.delete();

        file=new File(headUrl+"\\"+removedPhoneNumber+"\\personal_chats\\"+user.getPhoneNumber()+".txt");
        file.delete();

        file=new File(headUrl+"\\"+removedPhoneNumber+"\\friends\\"+user.getPhoneNumber()+".txt");
        file.delete();

        System.out.println("SUCCESSFULLY DELETED");
    }



    public List<User> getAllFriends(String headUrl, User user) throws IOException {
        List<User> friends = new ArrayList<>();
        File file = new File(headUrl + "\\" + user.getPhoneNumber() + "\\friends");
        for (File file1 : file.listFiles()) {
            FileReader fileReader = new FileReader(file1);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            friends.add(gson.fromJson(fileReader, User.class));
            fileReader.close();
        }
        return friends;
    }
    public List<String> getAllBirthdays(String headUrl,User currentUser) throws IOException {
        List<String> birthdays=new ArrayList<>();
        for (User user : getAllFriends(headUrl, currentUser)) {
            String NDMY=user.getFirstName()+" "+user.getLastName()+" : "+user.getMonth()+"-"+user.getBirthday()+"-"+user.getYear();
            birthdays.add(NDMY);
        }
        return birthdays;
    }
}
