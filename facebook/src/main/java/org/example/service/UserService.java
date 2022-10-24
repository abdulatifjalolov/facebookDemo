package org.example.service;

import org.example.model.User;

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
        user1.setLastName(firstName);
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


    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
