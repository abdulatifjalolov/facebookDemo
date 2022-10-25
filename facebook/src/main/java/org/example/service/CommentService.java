package org.example.service;

import org.example.model.Comments;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CommentService implements BaseService{
    static ArrayList<Comments> comments =new ArrayList<>();

//    public boolean addComment(int postId, int userId,String description) {
//        Comments comment=new Comments();
//        comment.setUserId(userId);
//        comment.setPostId(postId);
//        comment.setDescription(description);
//        if (comment!=null){
//            comments.add(comment);
//            byte [] bytes=description.getBytes();
//            File file=new File("F:/Comment");
//            file.mkdir();
//            File file1 = new File(file, comment.getId()+".txt");
//            try {
//                file1.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            FileOutputStream fileOutputStream=null;
//            try {
//                fileOutputStream=new FileOutputStream(file1,true);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                fileOutputStream.write(bytes);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            return true;
//        }
//        return false;
//    }

//    public void showListCommentByPostId(int postId){
//
//        FileInputStream fileInputStream=new FileInputStream();
//        Comments comments1=new Comments();
//        if (comments1.getPostId()==postId) {
//            System.out.println(comments1);
//        }
//    }


    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}