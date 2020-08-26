package duke.storage;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load saved file from disk.
     *
     * @return list containing the previously saved tasks.
     */
    public TaskList load() {
        return TextToTaskListConverter.readFile(filePath);
    }


    /**
     * Save file to disk.
     *
     * @param tasks containing the tasks in the list.
     */
    public void save(TaskList tasks) {
        ArrayListToTextConverter.convertTaskListToText(tasks, filePath);
    }
}
