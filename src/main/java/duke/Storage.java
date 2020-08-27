package duke;

import duke.tasks.Task;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle storing and reading task data from the disk.
 */
public class Storage {

    private final Path FILE_PATH;

    /**
     * Creates a Storage object to handle a file at the given file path.
     *
     * @param filePath Path object to the file
     */
    public Storage(Path filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Saves the task list to the file.
     *
     * @param taskList List of Task objects to write to file
     */
    public void storeList(List<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH.toString());

            for (Task task : taskList) {
                writer.write(task.fileString() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads task list from the file.
     *
     * @return List of strings representing the tasks
     */
    public List<String> loadList() {
        List<String> stringList = new ArrayList<>();
        try {
            BufferedReader reader = java.nio.file.Files.newBufferedReader(FILE_PATH);

            String line;
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return stringList;
    }
}
