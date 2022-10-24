package org.example.service;

import org.example.model.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupService {
    public List<Group> groups=new ArrayList<>();

//    public boolean createGroup(String name,int adminId){
////        File file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\"+adminId);
//
//        Group group=new Group();
//        group.setName(name);
//        group.setAdminId(adminId);
//        groups.add(group);
//
//        File file=new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\groups\\"+group.getId()+".txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        String currentGroup=group.toString()+"\n";
//        byte [] bytes=currentGroup.getBytes();
//
//        FileOutputStream fileOutputStream= null;
//        try {
//            fileOutputStream = new FileOutputStream(file,true);
//            fileOutputStream.write(bytes);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
}
