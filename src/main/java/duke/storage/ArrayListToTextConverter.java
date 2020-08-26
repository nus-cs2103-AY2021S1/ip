package duke.storage;

import duke.tasks.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Convert arraylist to txt file.
 */
public class ArrayListToTextConverter {

    /**
     * @param tasks list containing the tasks to be converted to a txt file.
     * @param filepath path to store the file.
     */
    public static void convertArrayListToText(List<Task> tasks, String filepath) {
        try {
            FileOutputStream writeData = new FileOutputStream(filepath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(tasks);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
