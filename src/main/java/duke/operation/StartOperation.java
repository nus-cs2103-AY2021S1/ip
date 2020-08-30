package duke.operation;

import com.sun.prism.ResourceFactoryListener;
import duke.result.Result;
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
     * @return a <code>Result</code> containing the status of the loading.
     */
    @Override
    public Result execute() {
        String message = this.taskStorage.loadTaskList(taskList);
        return new Result(true, message, this.isExit());
    }
}
