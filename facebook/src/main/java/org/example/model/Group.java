package org.example.model;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Group extends Base{
  private String name;
  private int adminId;

}
