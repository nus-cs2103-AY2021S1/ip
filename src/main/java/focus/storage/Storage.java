package focus.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import focus.task.Task;

/** Allows Focus to load and save data in user's files. */
public class Storage {
    /** Represents the path for the task list to be saved at. */
    private final String path;
    /** Represents the current user's directory. */
    private static final String USER_DIRECTORY = "user.dir";

    /** Creates a storage to allow loading and saving of tasks. */
    public Storage() {
        this.path = System.getProperty(USER_DIRECTORY) + "/data/focus.txt";
    }

    /** Creates a folder to store text file. If present, it will not create. */
    public static void createFolder() {
        String folderPath = System.getProperty(USER_DIRECTORY) + "/data";
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
     *
     * @return True if user has an existing text file,
     * false if user does not have an existing text file.
     */
    public boolean retrieveTextFile() {
        File data = null;
        boolean hasCreatedTextFile = false;
        boolean hasTextFile = false;
        try {
            data = new File(path);
            hasCreatedTextFile = data.createNewFile();
        } catch (IOException e) { // creation or retrieving data has errors
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        if (hasCreatedTextFile) {
            System.out.println("Text file created: " + data.getName());
        } else {
            hasTextFile = true;
            System.out.println("Text file already exists.");
        }
        return hasTextFile;
    }

    /**
     * Loads the data from user's text file if it already exists.
     *
     * @return Arraylist of tasks in string format.
     */
    public ArrayList<String> loadData() {
        ArrayList<String> taskList = new ArrayList<>();
        try {
            File data = new File(path);
            Scanner sc = new Scanner(data);
            String task = "";
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
     *
     * @param item Item to be added to text file.
     */
    public void addData(Task item) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            String task = item.taskToText() + "\n";
            assert !task.isEmpty() : "Task should not be blank here.";
            fileWriter.write(task);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Updates the data in user's text file.
     *
     * @param tasks Task list to be updated.
     */
    public void updateData(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (Task item : tasks) {
                String task = item.taskToText() + "\n";
                assert !task.isEmpty() : "Task should not be blank here.";
                fileWriter.write(task);
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
