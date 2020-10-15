package duke.storage;

import java.io.File;

import duke.parser.TaskStorageParser;
import duke.task.Task;

/** Class that loads and converts the <code>TaskList</code> in Duke with a text file. */
public class TaskStorage extends Storage<Task> {
    private static final String DEFAULT_FILENAME = "task_storage.txt";

    /**
     * Constructor method.
     * Instantiates the actual path to the text file and a <code>TaskStorageParser</code>.
     *
     * @param path the path of the directory.
     */
    TaskStorage(String path) {
        super(new File(path + DEFAULT_FILENAME), new TaskStorageParser());
    }

    @Override
    String getStorableName() {
        return "task";
    }
}
