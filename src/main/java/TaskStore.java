package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskStore {
    private final String filePath;
    private File taskStorage;
    private ArrayList<Task> listOfTasks;

    public TaskStore(String filePath) {
        this.filePath = filePath;
        try {
            taskStorage = new File(filePath);
            taskStorage.getParentFile().mkdirs();
            taskStorage.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating storage file");
        }
    }

    public void writeTaskToFile(String taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskToAdd);
        fw.close();
    }

    public void loadTasksFromStorage() throws FileNotFoundException {
        Scanner s = new Scanner(taskStorage); // create a Scanner using the File as the source
        ArrayList<Task> listOfTasks = new ArrayList<>();
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static void saveTasksToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


}
