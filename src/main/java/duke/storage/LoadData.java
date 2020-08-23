package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadData {
    /**
     * Loads saved tasks
     * @param path
     * @return commands
     */
    public static ArrayList<String> getSavedTasks(String path) {
        ArrayList<String> savedTasks = new ArrayList<>();
        try {
            File savedFile = new File(path);
            Scanner scanner = new Scanner(savedFile);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                //System.out.println(taskData);
                savedTasks.add(taskData);
            }
        } catch (FileNotFoundException e) {
            savedTasks.add("000");
            //e.printStackTrace();
        }
        return savedTasks;
    }
}
