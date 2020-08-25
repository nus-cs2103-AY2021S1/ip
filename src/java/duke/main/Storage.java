package duke.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final File file;

    Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Reads a saved file and generates a TaskList from its contents.
     * If the directory or file is missing, a new empty text file is generated with its
     * parent directory.
     *
     * @return TaskList representing all the tasks in the saved file (if any).
     */
    TaskList getTasksFromFile() {
        File dir = new File(file.getParent());
        try {
            if (dir.mkdir()) {
                System.out.println("Setting up file paths...");
            }
            if (file.createNewFile()) {
                System.out.println("Uh oh, I couldn't find any saved tasks, "
                        + "so I just made a new save for you!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TaskList(file);
    }

    /**
     * Writes the contents of a given TaskList to its saved file.
     * This method will overwrite the contents of the file upon being called.
     *
     * @param taskList List of tasks to be written and saved to a file.
     */
    void write(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(file.getPath());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(taskList.write());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
