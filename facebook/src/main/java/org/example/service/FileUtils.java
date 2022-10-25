package org.example.service;

import com.google.gson.Gson;
import org.example.model.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {
    public static void createFileForUser(User user, String url){
        if (user!=null) {
            File file = new File(url + "\\"+user.getPhoneNumber());
            file.mkdir();
        }
    }

    public static User getObjectByName(String headUrl, String name ) throws FileNotFoundException {
        File file=new File(headUrl);
        FileReader fileReader=new FileReader(file);
        User user=new Gson().fromJson(fileReader, User.class);
        return user;
    }

    public static void createChildFiles(String headUrl,String phoneNumber){
        File file = new File(headUrl+"\\" + phoneNumber+"\\friends");
        file.mkdir();

        file = new File(headUrl+"\\" + phoneNumber+ "\\groups");
        file.mkdir();

        file = new File(headUrl+"\\" + phoneNumber+ "\\posts");
        file.mkdir();

        file = new File(headUrl+"\\" + phoneNumber+ "\\personal_chats");
        file.mkdir();
    }
}
