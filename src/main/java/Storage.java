package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    Scanner data;
    public Storage(String filePath){
        File file= new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.data = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<String> read() {
        List<String> list = new ArrayList<>();
        while (data.hasNextLine()) {
            list.add(data.nextLine());
        }
        return list;
    }

    public void write(List<String> data) throws IOException {
        FileWriter fw = new FileWriter("data.txt");
        for (String s : data) {
            fw.write(s + "\n");
        }
        fw.close();
    }
}
