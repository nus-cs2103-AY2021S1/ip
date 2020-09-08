package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * <h1> Storage Class (Saving Tasks)</h1>
 * Dukefile class is the class that contains the methods
 * that manages the task within the single initialisation of the bot
 * and extracts the current tasks and stores it in a text file.
 *
 * @author Lee Penn Han.
 * @version 1.0.
 * @since 2020-25-08.
 */
public class Storage {
    private File file;
    private TaskList taskList;

    private Storage(String fileName, TaskList taskList) {
        this.file = new File(fileName);
        this.taskList = taskList;
    }

    /**
     * Loads the saved tasks that have not been archived.
     * @return an ArrayList of the past Tasks
     * @throws IOException on input error.
     */
    public ArrayList<String> loadFile() throws IOException {
        assert this.file != null : "file cannot be null";
        ArrayList<String> recordArrayLst = new ArrayList<>();
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextLine()) {
            recordArrayLst.add(sc.nextLine());
        }
        return recordArrayLst;
    }

    /**
     * Saves the current list of task to a textfile.
     * By iterating through the records Arraylist,
     * the method will save each element as a single task
     * in the text file.
     *
     * @author Lee Penn Han.
     * @throws IOException on input error.
     */
    public String saveToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask();
            fileWriter.write(task.toString() + "\n");
        }
        fileWriter.close();
        return "Total number of Tasks saved: " + taskList.getListSize();
    }

    /**
     * Instantiates a Storage object.
     *
     * @author Lee Penn Han.
     * @param fileName This is the filename that the tasks will be saved under.
     * @return Storage object.
     */
    public static Storage createDukeFile(String fileName, TaskList taskList) {
        return new Storage(fileName, taskList);
    }
}

