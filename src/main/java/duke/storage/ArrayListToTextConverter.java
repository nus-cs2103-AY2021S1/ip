package duke.storage;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import duke.tasks.TaskList;

/**
 * Saves the current TaskList.
 */
public class ArrayListToTextConverter {

    /**
     * @param tasks TaskList containing all the existing tasks.
     * @param filePath Filepath to store the file.
     */
    public static void convertTaskListToText(TaskList tasks, String filePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tasks);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
