package org.example.model;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public abstract class Base {
    protected int id;
    private static int idGeneration=0;
    public Base(){
        idGeneration++;
        this.id=idGeneration;
    }


}
