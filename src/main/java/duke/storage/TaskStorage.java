package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.StorageParser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that loads and converts the <code>TaskList</code> in Duke with a text file.
 */
public class TaskStorage {
    private static final String DEFAULT_FILENAME = "taskstorage.txt";
    private static final String DEFAULT_FILEPATH = "src/main/java/duke/storage/";

    private final File file;
    private final StorageParser storageParser;

    private TaskStorage(File file) {
        this.file = file;
        this.storageParser = new StorageParser();
    }

    /**
     * Creates a <code>TaskStorage</code>.
     * The path to the file used is pre determined by default.
     * If the path directory does not exist, the file will then be saved into the root of the directory.
     * @return a preconfigured <code>TaskStorage</code>.
     */
    public static TaskStorage createTaskStorage() {
        File f = new File(DEFAULT_FILEPATH);
        File actualFile = f.exists()
                ? new File(DEFAULT_FILEPATH + DEFAULT_FILENAME)
                : new File(DEFAULT_FILENAME);
        return new TaskStorage(actualFile);
    }

    /**
     * Loads the <code>TaskList</code> from the text file.
     * @param taskList the <code>TaskList</code> to be loaded onto.
     * @return a <code>String</code> indicating the status of the loading.
     */
    public String loadTaskList(TaskList taskList) {
        Scanner s;
        try {
            s = new Scanner(this.file);
        } catch (IOException ignore) {
            return "I detect no storage files. I shall create a fresh list.";
        }

        StringBuilder sb = new StringBuilder();
        while (s.hasNext()) {
            try {
                Task task = this.storageParser.convertStorageToTask(s.nextLine());
                taskList.addTask(task);
            } catch (DukeException exception) {
                sb.append(exception.getMessage());
                sb.append("\n");
            }
        }

        if (sb.length() == 0) {
            return "All tasks have been loaded.";
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return "The following tasks could not be loaded:\n" + sb.toString();
        }
    }

    private void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        fw.write(text);
        fw.close();
    }

    /**
     * Saves the <code>TaskList</code> into the text file.
     * @param taskList the <code>TaskList</code> that is to be saved.
     * @throws DukeException if the text file cannot be written onto.
     */
    public void saveToDisk(TaskList taskList) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String storageTask = this.storageParser.convertTaskToStorage(task);
            sb.append(storageTask);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeException("There were some problems when writing to the file. "
                    + exception.getMessage());
        }
    }
}
