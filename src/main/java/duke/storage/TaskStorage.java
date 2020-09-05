package duke.storage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeOperationException;
import duke.exception.DukeParseException;
import duke.list.TaskList;
import duke.parser.TaskStorageParser;
import duke.task.Task;

/** Class that loads and converts the <code>TaskList</code> in Duke with a text file. */
public class TaskStorage extends Storage {
    private static final String DEFAULT_FILENAME = "task_storage.txt";
    private final TaskStorageParser taskStorageParser;

    /**
     * Constructor method.
     * Instantiates the actual path to the text file and a <code>TaskStorageParser</code>.
     *
     * @param path the path of the directory.
     */
    TaskStorage(String path) {
        super(new File(path + DEFAULT_FILENAME));
        this.taskStorageParser = new TaskStorageParser();
    }

    /**
     * Loads the <code>TaskList</code> from the text file.
     *
     * @param taskList the <code>TaskList</code> to be loaded onto.
     * @return a <code>String</code> indicating the status of the loading.
     */
    public String loadTaskList(TaskList taskList) {
        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (IOException ignore) {
            return "I detect no task storage files. I shall create a fresh list.";
        }

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            try {
                Task task = this.taskStorageParser.convertStorageToTask(sc.nextLine());
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


    /**
     * Saves the <code>TaskList</code> into the text file.
     *
     * @param taskList the <code>TaskList</code> that is to be saved.
     * @throws DukeOperationException if the text file cannot be written onto.
     */
    public void saveToDisk(TaskList taskList) throws DukeOperationException {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String storageTask = this.taskStorageParser.convertTaskToStorage(task);
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
