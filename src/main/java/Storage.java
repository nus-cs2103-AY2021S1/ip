package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The storage deals with loading tasks from the file and saving tasks in the file
 *
 * @author Lio
 */
public class Storage {
    private Scanner data;

    /**
     * Constructor
     *
     * @param filePath File path of the storage file
     */
    public Storage(String filePath) throws Exception {
        File file = new File(filePath);
        try {
            Boolean fileCreated = file.createNewFile();
        } catch (IOException e) {
            throw new Exception("Cannot create storage file :(");
        }
        try {
            this.data = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new Exception("Cannot find storage file :(");
        }
    }

    /**
     * Reads the storage file
     *
     * @return List of stored data
     */
    public List<String> read() {
        List<String> list = new ArrayList<>();
        while (data.hasNextLine()) {
            list.add(data.nextLine());
        }
        return list;
    }

    /**
     * Writes the TaskList into the storage file
     *
     * @param data TaskList in data form
     * @throws IOException If it is unable to write to the storage file
     */
    public void write(List<String> data) throws IOException {
        FileWriter fw = new FileWriter("data.txt");
        for (String s : data) {
            fw.write(s + "\n");
        }
        fw.close();
    }
}
