package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Post extends Base{
    private int userId;
    private String postName;
    private String desctription;

}
