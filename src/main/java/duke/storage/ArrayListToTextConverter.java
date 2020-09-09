package duke.storage;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import duke.tasks.TaskList;

/**
 * Convert arraylist to txt file.
 */
public class ArrayListToTextConverter {

    /**
     * @param tasks    list containing the tasks to be converted to a txt file.
     * @param filePath path to store the file.
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
