package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import seedu.duke.todo.Task;

/**
 * Represents a storage. Allow Duke to read and write to local disk.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";
    private TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Reads files from local disk and print out the content.
     */
    public void readFile() {
        // load the file first
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File f = new File(FILE_PATH);
        try {
            if (f.createNewFile()) {
                // file does not exist
                System.out.println("A data file has been created for you");
            } else {
                // file exist
                System.out.println("Here are your existing tasks");
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskStr = s.nextLine();
                    System.out.println(taskStr);
                    // load the task
                    Task savedTask = Parser.parseTaskFromStorage(taskStr);
                    taskList.tasks.add(savedTask);
                }
            }
            System.out.println("What do you want to do today?");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves tasks to local disk.
     * @param taskList The task list that is to be saved.
     */
    public void saveTasks(TaskList taskList) {
        if (taskList.tasks.size() == 0) {
            return;
        } else {
            writeToFile(FILE_PATH, taskList.tasks.get(0).getData());
            for (int i = 1; i < taskList.tasks.size(); i++) {
                String taskData = taskList.tasks.get(i).getData();
                appendToFile(FILE_PATH, taskData);
            }
        }
    }

    /**
     * Append text to local file at filePath.
     * @param filePath The relative path of the file.
     * @param textToAdd The text to be added.
     */
    public void appendToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Write text to local file at filePath. This overwrites the whole file.
     * @param filePath The relative path of the file.
     * @param textToAdd The text to be added.
     */
    public void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
