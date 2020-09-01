package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TaskStorage {
    /**
     * saveTask is used to store current tasks into a txt file
     * @param prev_tasks File object to be written to
     * @param tasks TaskList to be saved
     */
    public static void saveTask(File prev_tasks, TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(prev_tasks));
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i).getFileString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
