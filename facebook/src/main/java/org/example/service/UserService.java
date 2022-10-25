package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService implements BaseService {
    static List<User> users = new ArrayList<>();

    public String[] usersList() {
        List<String> usersList = new ArrayList<>();
        String headUrl = "C:\\Users\\abdulatif\\forJAVA\\facebook\\allUsers";
        File file = new File(headUrl);
        return file.list();
    }

    public boolean isHasUser(User user) {
        for (String s : usersList()) {
            if (s.equals(user.getPhoneNumber())){
                return false;
            }
        }
        return true;
    }

    public User logIn(String password, String phoneNumber, String headUrl) throws FileNotFoundException {
        String childUrl=headUrl+"//"+phoneNumber+"//"+phoneNumber+".txt";
        User user = FileUtils.getObjectByName(childUrl,phoneNumber);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUsersByPhoneNumber(String phoneNumber,String headUrl) throws FileNotFoundException {
        return FileUtils.getObjectByName(phoneNumber, headUrl);
    }

    public void addUsersInfoInGson(User user, String headUrl) throws IOException {
        if (user != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(headUrl+ "\\"+ user.getPhoneNumber() + "\\" + user.getPhoneNumber() + ".txt");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String curr = gson.toJson(user);
            byte[] bytes = curr.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        }
    }


    @Override
    public Object getById(int id) {
        for (User user : users) {
            if (user != null) {
                if (user.getId() == id) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
