package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage() {
        this.path = System.getProperty("user.dir") + "/data/duke.txt";
    }

    public static void createFolder() {
        String folderPath = System.getProperty("user.dir") + "/data";
        File folder = new File(folderPath);
        boolean isSuccessful = folder.mkdir();
        if (isSuccessful) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("Folder already exists.");
        }
    }

    public boolean retrieveTextFile() {
        boolean hasTextFile = false;
        try {
            File data = new File(path);
            if (data.createNewFile()) {
                System.out.println("Text file created: " + data.getName());
            } else {
                hasTextFile = true;
                System.out.println("Text file already exists.");
            }
        } catch (IOException e) { // creation or retrieving data has errors
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return hasTextFile;
    }

    public ArrayList<String> loadData() {
        String task;
        ArrayList<String> taskList = new ArrayList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {
                task = sc.nextLine();
                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }

    public void saveData(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Task item : taskList) {
                // get the task list items
                String task = item.taskToText() + "\n";
                fileWriter.write(task);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
