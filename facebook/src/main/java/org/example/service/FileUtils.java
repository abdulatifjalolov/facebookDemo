package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.User;

import java.io.*;

public class FileUtils {
    public static void createFileForUser(User user, String url) {
        if (user != null) {
            File file = new File(url + "\\" + user.getPhoneNumber());
            file.mkdir();
        }
    }

    public static User getObjectByName(String headUrl, String name) throws IOException {
        String userAddress="\\"+name+"\\"+name+".txt";
        File file = new File(headUrl+userAddress);
        FileReader fileReader = new FileReader(file);
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        User user = gson.fromJson(fileReader, User.class);
        fileReader.close();
        return user;
    }

    public static void createChildFiles(String headUrl, String phoneNumber) {
        File file = new File(headUrl + "\\" + phoneNumber + "\\friends");
        file.mkdir();

        file = new File(headUrl + "\\" + phoneNumber + "\\groups");
        file.mkdir();

        file = new File(headUrl + "\\" + phoneNumber + "\\posts");
        file.mkdir();

        file = new File(headUrl + "\\" + phoneNumber + "\\personal_chats");
        file.mkdir();

        file = new File(headUrl + "\\" + phoneNumber + "\\requests");
        file.mkdir();
    }

    public static File createFileForFriend(String friendUrlAddress){
        File file=new File(friendUrlAddress);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    public static void writeFriendToFriendsList(User friend,File file){
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String currFriend=gson.toJson(friend);
        byte [] bytes=currFriend.getBytes();
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
