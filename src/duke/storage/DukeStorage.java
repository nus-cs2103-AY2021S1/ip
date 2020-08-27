package duke.storage;

import duke.Duke;

import java.io.IOException;

/**
 * DukeStorage helps Duke saving and loading data.
 */
public class DukeStorage {

    private Duke duke;
    private TaskStorage taskStorage;

    /**
     * Constructs a DukeStorage object.
     * @param duke the duke that will use this DukeStorage
     */
    public DukeStorage(Duke duke) {
        this.duke = duke;
        taskStorage = duke.taskStorage;
    }

    /**
     * Loads saved tasks to the current task list.
     */
    public void loadSavedTasks() {
        duke.taskList.setTasks(taskStorage.getSavedTasks());
    }

    /**
     * Saves all current tasks in task list into the task txt files.
     * @throws IOException
     */
    public void saveCurrentTasks() throws IOException {
        taskStorage.saveTasks(duke.taskList.getTasks());
    }
}
