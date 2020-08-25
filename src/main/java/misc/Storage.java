package main.java.misc;

import main.java.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    /**
     * Reads the storage.txt created in ./tmp/data, or create a new one if the file cannot be found.
     * @return List of task in the file. Each task is represented with a list of tokens.
     */
    public static List<List<String>> readFile() {
        List<List<String>> out = new ArrayList<>();
        File dir = new File("./tmp/data");
        dir.mkdirs();
        File f = new File(dir, "storage.txt");
        try {
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] tokens = s.nextLine().split("%%%");
                List<String> tokenss = new ArrayList<>(Arrays.asList(tokens));
                //tokenss.add(2, "null");
                out.add(tokenss);
            }
            return out;
        } catch (IOException e) {
            throw new Error("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Overwrites the file ./tmp/data/storage.txt with information from the current list of tasks.
     * @param database the current list of tasks
     */
    public static void writeFile(List<Task> database) {
        File dir = new File("./tmp/data");
        try {
            FileWriter fw = new FileWriter(new File(dir, "storage.txt"));
            for (int i = 0; i < database.size(); i++) {
                Task task = database.get(i);
                fw.write(task.serialize() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
