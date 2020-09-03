package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TaskStorage {
    /**
     * saveTask is used to store current tasks into a txt file
     * @param prevTasks File object to be written to
     * @param tasks TaskList to be saved
     */
    public static void saveTask(File prevTasks, TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(prevTasks));
            for (int i = 0; i < tasks.getSize(); i++) {
                writer.write(tasks.getTask(i).getFileString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
