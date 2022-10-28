package org.example.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post extends Base {
    private int userId;
    private String postName;
    private String description;

    @Override
    public String toString() {
        return "<post heme> '" + postName + "'\ndescription: '" + description + "'\n";
    }
}
