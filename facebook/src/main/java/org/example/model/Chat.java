package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Chat extends Base{
    private int user1Id;
    private int user2Id;

}
