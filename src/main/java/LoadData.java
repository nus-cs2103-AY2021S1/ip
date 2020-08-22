import Task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadData {
    public ArrayList<String> getSavedTasks() {
        ArrayList<String> savedTasks = new ArrayList<String>();
        try {
            File savedFile = new File("data/save_file.txt");
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
