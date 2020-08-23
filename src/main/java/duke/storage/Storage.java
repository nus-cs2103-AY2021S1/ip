package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allows Focus to load and save data in user's files.
 */
public class Storage {
    /**
     * Represents the path for the task list to be saved at.
     */
    private final String path;

    /**
     * Creates a storage to allow loading and saving of tasks.
     */
    public Storage() {
        this.path = System.getProperty("user.dir") + "/data/duke.txt";
    }

    /**
     * Creates a folder to store text file. If present, it will not create.
     */
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

    /**
     * Creates or retrieves the text file from user's files.
     * @return True if user has an existing text file,
     * false if user does not have an existing text file.
     */
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

    /**
     * Loads the data from user's text file if it already exists.
     * @return Arraylist of tasks in string format.
     */
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

    /**
     * Adds the data to user's text file.
     * @param item Item to be added to text file.
     */
    public void addData(Task item) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            String task = item.taskToText() + "\n";
            fileWriter.write(task);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the data in user's text file.
     * @param tasks Task list to be updated.
     */
    public void updateData(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Task item : tasks) {
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
