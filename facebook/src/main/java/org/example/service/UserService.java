package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements  BaseService{
    static List<User> users= new ArrayList<>();
    public boolean createUser(String firstName,String lastName,String phoneNumber,String password,String month,int birthday,int year,String gander){
        for (User user : users) {
            if (user!=null) {
                if (user.getPhoneNumber().equals(phoneNumber)) {
                    return false;
                }
            }
        }
        User user1=new User();
        user1.setPhoneNumber(phoneNumber);
        user1.setFirstName(firstName);
        user1.setLastName(lastName);
        user1.setMonth(month);
        user1.setGander(gander);
        user1.setBirthday(birthday);
        user1.setYear(year);
        user1.setPassword(password);
        users.add(user1);
        return true;
    }
    public User logIn(String password,String phoneNumber){
        for (User user : users) {
            if (user!=null){
                if (user.getPhoneNumber().equals(phoneNumber)&&user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }
    public Integer getIdByPhoneNumber(String phoneNumber){
        for (User user : users) {
            if (user!=null){
                if (user.getPhoneNumber().equals(phoneNumber)){
                    return user.getId();
                }
            }
        }
        return null;
    }
    public void addUserInFiles(String phoneNumber,boolean created) throws IOException {
        if (created) {
            File file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" +getIdByPhoneNumber(phoneNumber));
            file.mkdir();
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" +getIdByPhoneNumber(phoneNumber) + "\\" +getIdByPhoneNumber(phoneNumber) + ".txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\chats");
            file.mkdir();
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\groups");
            file.mkdir();
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\posts");
            file.mkdir();
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\comments");
            file.mkdir();
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\friends");
            file.mkdir();
            file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\likes");
            file.mkdir();

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream("C:\\Users\\abdulatif\\forJAVA\\facebook\\" + getIdByPhoneNumber(phoneNumber) + "\\" + getIdByPhoneNumber(phoneNumber) + ".txt");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Gson gson=new GsonBuilder().setPrettyPrinting().create();
            String curr =gson.toJson(getById(getIdByPhoneNumber(phoneNumber)));
            byte[] bytes = curr.getBytes();

            try {
                fileOutputStream.write(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                fileOutputStream.close();
            }
        }
    }


    @Override
    public Object getById(int id) {
        for (User user : users) {
            if (user!=null){
                if (user.getId()==id) {
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
