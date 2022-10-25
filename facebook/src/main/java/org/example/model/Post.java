package org.example.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Post extends Base{
    private int userId;
    private String postName;
    private String description;

}
