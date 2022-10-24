package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Group extends Base{
  private String name;

}
