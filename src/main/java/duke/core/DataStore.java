package duke.core;

import duke.core.task.Task;
import duke.designpattern.command.UndoRedoList;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the centralised storage for the core components of Duke.
 * A data store contains every data structures that Duke uses.
 * It facilitates passing of such data structures throughout the application.
 */
public class DataStore {

    private final List<Task> taskList;
    private final UndoRedoList history;

    /**
     * Initialize DataStore with default settings
     */
    public DataStore() {
        this.taskList = new ArrayList<>(100);
        this.history = new UndoRedoList();
    }

    /**
     * Initialize DataStore with specified taskList
     * @param taskList The List of Task being managed by Duke
     */
    public DataStore(List<Task> taskList) {
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
