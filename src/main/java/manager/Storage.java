package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Represents a platform enabling storage, which stores
 * and retrieves the task list from the hard disk.
 */
public class Storage {

    private String pathname;
    private File dataFile;

    /**
     * Initializes the database. If database file does not exist,
     * creates a new directory consisting of that file and
     * returns the Storage object. Else, only returns the Storage object.
     * @return database created through Storage object
     */
    public static Storage initializeDatabase() {
        return new Storage().initialize();
    }

    private Storage initialize() {
        String rootPath = new File(System.getProperty("user.dir"))
                .getParentFile()
                .getPath();
        this.pathname = String.format("%s/data/database.txt", rootPath);
        try {
            this.dataFile = new File(this.pathname);
            this.dataFile.getParentFile().mkdirs();
            this.dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("File already exists.");
        }
        return this;
    }

    /**
     * Retrieves the task list from the database file.
     * @return task list retrieved
     */
    public List<Task> retrieveTaskList() {
        List<Task> taskList = new ArrayList<>();
        try {
            File dataFile = new File(this.pathname);
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                taskList.add(createSavedTask(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file does not exist.");
        }
        listSavedTasks(taskList);
        return taskList;
    }

    /**
     * Lists out the saved tasks from the specified task list.
     * @param taskList as specified
     */
    public void listSavedTasks(List<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("Oohhh, your task list is empty. Let's add some tasks!");
        } else {
            System.out.println("Oohhh okay, remember you have these tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * Creates the saved task from the string input of a database
     * @param string input of task
     * @return task created from input string
     */
    public Task createSavedTask(String string) {
        String[] words = string.split(" \\| ");
        String taskType = words[0];
        boolean isDone = words[1].equals("true");
        String description = words[2];
        String tags = words.length <= 3 ? "" : words[3];

        switch (taskType) {
        case "T":
            return new Todo(description, isDone, tags);
        case "D":
            String endTime = words[4];
            return new Deadline(description, endTime, isDone, tags);
        case "E":
            String time = words[4];
            return new Event(description, time, isDone, tags);
        default:
            assert false : "Unknown task type from storage";
            return null;
        }
    }

    /**
     * Updates the database file to save task list for storage.
     * @param taskList to be saved
     */
    public void updateDatabase(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.databaseString() + "\n");
        }
        try {
            FileWriter fileWriter = new FileWriter(this.pathname);
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot update database file.");
        }
    }
}
