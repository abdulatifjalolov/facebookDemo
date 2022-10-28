package org.example.model;

import lombok.Getter;
import lombok.ToString;

import java.io.*;

@ToString
@Getter
public abstract class Base {
    protected int id;
    private static final int idGeneration = 0;

    public Base() {
        File file = new File("C:\\Users\\abdulatif\\forJAVA\\facebook\\idGeneration\\idGeneration.txt");
        FileReader fileReader = null;
        int idGeneration1 = 0;
        try {
            fileReader = new FileReader(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            idGeneration1 = fileReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        idGeneration1++;

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.write(idGeneration1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.id = idGeneration1;
    }

}
