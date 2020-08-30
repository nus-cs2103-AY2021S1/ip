package duke.operation;

import duke.result.Result;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Abstract class representing the operations that add various types of <code>Tasks</code>.
 */
public abstract class AddOperation extends Operation {
    protected String description;
    protected TaskList taskList;

    /**
     * Constructor method.
     * @param description the description of the <code>Task</code>.
     * @param taskList the <code>TaskList</code> that <code>Task</code> is to be added into.
     */
    AddOperation(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
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
     * Creates the associated <code>Task</code>.
     * @return an uncompleted <code>Task</code>.
     */
    public abstract Task createTask();

    /**
     * Adds the <code>Task</code> into the <code>TaskList</code>.
     * @return a <code>Result</code> specifying the <code>Task</code> that has been added and
     * the total number of <code>Tasks</code> in <code>TaskList</code>.
     */
    @Override
    public Result execute() {
        Task newTask = createTask();
        this.taskList.addTask(newTask);
        String message = "I have added the task:\n" + newTask + "\n"
                + String.format("You now have %d tasks.", this.taskList.getCurrCapacity());
        return new Result(true, message, this.isExit());
    }
}
