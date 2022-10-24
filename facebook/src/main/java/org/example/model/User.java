package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends Base{
    private String firstName;
    private String lastName;
    private String phoneNumber;
//    private String email;
    private String password;
    private String month;
    private int birthday;
    private int year;
    private String gander;
    //public char U+2665;
    private int chatId;
    private int chatGroupId;
}
