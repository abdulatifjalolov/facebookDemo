package org.example.service;

import org.example.model.User;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatService {

    public void createChatFile(User currentUser,String phoneNumber,String headUrl) throws IOException {
        File file=new File(headUrl+"\\"+currentUser.getPhoneNumber()+"\\personal_chats\\"+phoneNumber+".txt");
        file.createNewFile();

        file=new File(headUrl+"\\"+phoneNumber+"\\personal_chats\\"+currentUser.getPhoneNumber()+".txt");
        file.createNewFile();
    }

    public List<File> getPersonalCHats(String headUrl,User currentUser,String phoneNumber){
        List<File> files=new ArrayList<>();
        File file1=new File(headUrl+"\\"+currentUser.getPhoneNumber()+"\\personal_chats\\"+phoneNumber+".txt");
        files.add(file1);

        File file2=new File(headUrl+"\\"+phoneNumber+"\\personal_chats\\"+currentUser.getPhoneNumber()+".txt");
        files.add(file2);

        return files;
    }

    public String getPersonalChat(String headUrl,User currentUser,String phoneNumber) throws IOException {
        File file1=new File(headUrl+"\\"+currentUser.getPhoneNumber()+"\\personal_chats\\"+phoneNumber+".txt");
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file1));
        String message ="";
        String s=bufferedReader.readLine();
        while (s!=null){
            message+=s+"\n";
            s=bufferedReader.readLine();
        }
        bufferedReader.close();
        return message;
    }
    public String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return simpleDateFormat.format(calendar.getTime());
    }
    public void writeMessagetoPersonalChat(File personalChat,String message) throws IOException {
        FileWriter fileWriter=new FileWriter(personalChat,true);
        fileWriter.write(message);
        String line=System.getProperty("line.separator");
        fileWriter.write(line);
        fileWriter.close();
    }

}
