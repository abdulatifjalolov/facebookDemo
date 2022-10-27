package org.example.dto;

import org.example.model.Post;
import org.example.model.User;

import java.util.Scanner;

public class PostDto {
    public static Post postDto(Post post, User currentUser){
        System.out.println("ENTER POST THEME: ");
        post.setPostName(new Scanner(System.in).nextLine());


        System.out.println("ENTER DESCRIPTION: ");
        post.setDescription(new Scanner(System.in).nextLine());

        post.setUserId(currentUser.getId());

        return post;
    }
}
