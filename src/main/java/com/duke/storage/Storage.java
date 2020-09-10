package com.duke.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.duke.exceptions.DukeException;
import com.duke.parser.Parser;
import com.duke.tasklist.TaskList;
import com.duke.tasks.Task;

/**
 * Represents a Storage.
 * Pulls data from a persistent file upon initialisation of application.
 * Saves data to persistent file upon termination of session.
 */

public class Storage {
    private static String FILE_PATH = "src/main/data/input.txt";
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Prints contents from persistent file.
     */
    public void printFileContents() {
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Loads tasks saved in storage file into List when program starts.
     *
     * @return List returns list with tasks saved as entries.
     * @throws DukeException throws DukeException if file fails to load.
     * @throws DukeException throws DukeException if entry in file has incorrectly saved format.
     */
    public List<Task> load() throws DukeException {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            List<Task> taskArr = new ArrayList<>();
            while (s.hasNext()) {
                String taskString = s.nextLine();
                Task task = Parser.parseTask(taskString);
                taskArr.add(task);
            }
            return taskArr;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new DukeException("File failed to load. Initializing new File...");
        } catch (DukeException e) {
            throw e;
        }
    }

    private static String parseTaskToString(Task task) {
        return task.parseToSaveFormat();
    }

    /**
     * Saves tasks from List into a persistent file.
     *
     * @throws IOException throws IOException if file saving fails.
     */
    public static void saveListToFile(TaskList taskList) throws DukeException {
        try {
            String input = "";
            FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
            for (int i = 0; i < taskList.getList().size(); i++) {
                List<Task> list = taskList.getList();
                input += parseTaskToString(list.get(i)) + "\n";
            }
            fw.write(input);
            fw.close();
        } catch (IOException ioException) {
            String errMessage = "Sorry! The file failed to save.";
            throw new DukeException(errMessage);
        }
    }

}
