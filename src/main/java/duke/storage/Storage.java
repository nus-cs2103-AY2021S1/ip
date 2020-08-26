package duke.storage;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Load saved file from disk.
     *
     * @return list containing the previously saved tasks.
     */
    public List<Task> load() {
        return TextToArrayListConverter.readFile(filepath);
    }


    /**
     * Save file to disk.
     *
     * @param tasks containing the tasks in the list.
     */
    public void save(TaskList tasks) {
        ArrayListToTextConverter.convertArrayListToText(tasks.getTaskList(), filepath);
    }
}
