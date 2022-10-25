package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Comments;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CommentService implements BaseService{
    static ArrayList<Comments> comments =new ArrayList<>();
    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}