package org.example.service;

import org.example.model.Group;
import org.example.model.User;
import java.io.*;
import java.util.*;

public class GroupService{

    public List<File> allGroups(String headUrl, User currentUser) {
        List<File> files = new ArrayList<>();
        File file = new File(headUrl + "\\" + currentUser.getPhoneNumber() + "\\groups");
        for (File file1 : file.listFiles()) {
            files.add(file1);
        }
        return files;
    }

    public List<String> getAllGroupsName(String headUrl, User currentUser) {
        List<String> list = new ArrayList<>();
        for (File file : allGroups(headUrl, currentUser)) {
            list.add(file.getName().replaceAll(".txt", ""));
        }
        return list;
    }

    public boolean isHasGroup(String headUrl, String groupName, User currentUser) {
        for (File file : allGroups(headUrl, currentUser)) {
            if (groupName.equals(file.getName())) {
                return true;
            }
        }
        return false;
    }

    public void createFileForGroup(String headUrl, String groupName, User currentUser, List<Integer> membersList) throws IOException {
        if (!isHasGroup(headUrl, groupName, currentUser)) {
            for (Integer user : membersList) {
                if (user != null && user != 0) {
                    String user1 = String.valueOf(user);
                    File file = new File(headUrl + "\\" + user1 + "\\groups\\" + groupName + ".txt");
                    file.createNewFile();
                }
            }
            System.out.println("SUCCESSFULLY ADDED");
        }
    }

    public File getGroupFile(String headUrl, String groupName, User currentUser) {
        File file=new File(headUrl+"\\"+currentUser.getPhoneNumber()+"\\groups\\"+groupName+".txt");
        return file;
    }

    public void writeMessageToFiles(File file,String message) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream(file,true);
        byte [] bytes=message.getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }


    public String getGroupChat(String headUrl, String groupName, User currentUser) throws IOException {
        File group = getGroupFile(headUrl, groupName, currentUser);
        FileReader fileReader=new FileReader(group);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String message = "";
        String s = bufferedReader.readLine();
        while (s != null) {
            message += s+"\n";
            s = bufferedReader.readLine();
        }
        bufferedReader.close();
        return message;
    }


    public boolean deleteMember(Group group, int memberId, List<Integer> membersIdList) {
        return false;
    }
}
