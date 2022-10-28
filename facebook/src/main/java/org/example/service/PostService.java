package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.dto.PostDto;
import org.example.model.Post;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PostService {

    public Post createNewPost(User currentUser) {
        Post post = new Post();
        Post post1 = PostDto.postDto(post, currentUser);
        return post1;
    }

    public void writePostInFile(User currentUser, String headUrl, Post post) throws IOException {
        File file = new File(headUrl + "\\" + currentUser.getPhoneNumber() + "\\posts\\" + post.getPostName() + ".txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        byte[] bytes = gson.toJson(post).getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    public List<Post> getAllPosts(String headUrl, String phoneNumber) throws FileNotFoundException {
        List<Post> posts = new ArrayList<>();
        File file = new File(headUrl + "\\" + phoneNumber + "\\posts");
        for (File currentFile : file.listFiles()) {
            posts.add(readPostInFile(currentFile));
        }
        return posts;
    }

    public Post readPostInFile(File currentFile) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Post post1 = gson.fromJson(new FileReader(currentFile), Post.class);
        return post1;
    }
}
