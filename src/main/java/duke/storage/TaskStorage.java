package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeOperationException;
import duke.exception.DukeParseException;
import duke.parser.StorageParser;
import duke.task.Task;
import duke.task.TaskList;

/** Class that loads and converts the <code>TaskList</code> in Duke with a text file. */
public class TaskStorage {
    private static final String DEFAULT_FILENAME = "taskstorage.txt";
    private static final String DEFAULT_FILEPATH = "data/";

    private final File file;
    private final StorageParser storageParser;

    private TaskStorage(File file) {
        this.file = file;
        this.storageParser = new StorageParser();
    }

    /**
     * Creates a <code>TaskStorage</code>.
     * The path to the file can be predetermined. If not, the default will be used.
     * If the path directory does not exist, it will be created.
     *
     * @param path the directory for the file to be stored in.
     * @return a preconfigured <code>TaskStorage</code>.
     */
    public static TaskStorage createTaskStorage(String... path) {
        String actualPath = path.length == 0
                ? DEFAULT_FILEPATH
                : path[0];
        File dir = new File(actualPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(actualPath + DEFAULT_FILENAME);
        return new TaskStorage(f);
    }

    /**
     * Loads the <code>TaskList</code> from the text file.
     *
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
            } catch (DukeParseException exception) {
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
     *
     * @param taskList the <code>TaskList</code> that is to be saved.
     * @throws DukeOperationException if the text file cannot be written onto.
     */
    public void saveToDisk(TaskList taskList) throws DukeOperationException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String storageTask = this.storageParser.convertTaskToStorage(task);
            sb.append(storageTask);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeOperationException("There were some problems when writing to the file. "
                    + exception.getMessage());
        }
    }
}
