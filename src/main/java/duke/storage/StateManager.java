package duke.storage;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class StateManager {
    private StateList stateList;
    private Storage storage;

    /**
     * A new StateManager object with Storage and StateList
     *
     * @param storagePath The path of data file for Storage
     * @throws DukeException If Storage could not be loaded
     */
    public StateManager(String storagePath) throws DukeException {
        storage = new Storage(storagePath);
        State currentState = new State(storage.loadTasks());
        stateList = new StateList(currentState);
    }

    /**
     * Returns a list of tasks from storage
     *
     * @return A list of tasks from storage
     * @throws DukeException If Storage could not load the list
     */
    public ArrayList<Task> getListOfTasks() throws DukeException {
        return storage.loadTasks();
    }

    /**
     * Update the state manager with a new state after an action is done
     *
     * @param taskList new TaskList to update the state manager with
     * @throws DukeException If storage does not load after updating
     */
    public void updateState(TaskList taskList) throws DukeException {
        storage.updateStorage(taskList);
        State currentState = new State(storage.loadTasks());
        stateList.newState(currentState);
    }

    /**
     * Returns a new Tasklist after undoing
     *
     * @return An updated TaskList
     * @throws DukeException If unable to undo or storage cannot be loaded after updating
     */
    public TaskList undo() throws DukeException {
        stateList.undoState();
        State newState = stateList.getState();
        TaskList newTaskList = new TaskList(newState.getListOfTasks());
        storage.updateStorage(newTaskList);
        return new TaskList(storage.loadTasks());
    }

    /**
     * Returns a TaskList after redoing
     *
     * @return An updated TaskList
     * @throws DukeException If unable to redo or cannot load storage
     */
    public TaskList redo() throws DukeException {
        stateList.redoState();
        State newState = stateList.getState();
        TaskList newTaskList = new TaskList(newState.getListOfTasks());
        storage.updateStorage(newTaskList);
        return new TaskList(storage.loadTasks());
    }
}
