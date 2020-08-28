package duke.operation;

import duke.storage.TaskStorage;
import duke.task.TaskList;

/**
 * The operation that initialises Duke.
 */
public class StartOperation extends Operation {
    private final TaskList taskList;
    private final TaskStorage taskStorage;

    /**
     * Constructor method.
     * @param taskList the <code>TaskList</code> for the storage file to be loaded onto.
     * @param taskStorage the <code>TaskStorage</code> instance that will read in the storage file.
     */
    public StartOperation(TaskList taskList, TaskStorage taskStorage) {
        this.taskList = taskList;
        this.taskStorage = taskStorage;
    }

    /**
     * Specifies that this is not an <code>ExitOperation</code>.
     * @return <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the operation by loading the storage file into <code>TaskList</code>.
     * @return a status <code>String</code> of the loading.
     */
    @Override
    public String execute() {
        String status = this.taskStorage.loadTaskList(taskList);
        return status;
    }
}
