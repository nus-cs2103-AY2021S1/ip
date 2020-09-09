package duke.core;

import java.util.ArrayList;
import java.util.List;

import duke.core.task.Task;
import duke.designpattern.command.UndoRedoList;

/**
 * A centralised storage for the core components of Duke.
 * It facilitates passing of such data structures throughout the application.
 */
public class DukeData {

    private final List<Task> taskList;
    private final UndoRedoList history;

    /**
     * Initialize DataStore with default settings
     */
    public DukeData() {
        this.taskList = new ArrayList<>(100);
        this.history = new UndoRedoList();
    }

    /**
     * Initialize DataStore with specified taskList
     * @param taskList The List of Task being managed by Duke
     */
    public DukeData(List<Task> taskList) {
        this.taskList = taskList;
        this.history = new UndoRedoList();
        assert taskList != null;
    }

    /**
     * Get the taskList referenced by this DataStore
     * @return A list of task referenced by this DataStore
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Get the UndoRedoList referenced by this DataStore
     * @return The UndoRedoList reference by this DataStore
     */
    public UndoRedoList getHistory() {
        return this.history;
    }
}
