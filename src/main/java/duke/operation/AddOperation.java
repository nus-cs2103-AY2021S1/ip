package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Abstract class representing the operations that add various types of Tasks.
 */
public abstract class AddOperation extends Operation {
    protected String description;
    protected TaskList taskList;

    /**
     * Constructor method.
     * @param description the description of the Task.
     * @param taskList the TaskList that Task is to be added into.
     */
    AddOperation(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    /**
     * Specifies that this is not an ExitOperation.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Creates the associated Task.
     * @return an uncompleted Task.
     */
    public abstract Task createTask();

    /**
     * Adds the Task into the TaskList.
     * @return a String specifying the Task that been added and the total number of Tasks in TaskList.
     */
    @Override
    public String execute() {
        Task newTask = createTask();
        this.taskList.addTask(newTask);
        String status = "I have added the task:\n" + newTask + "\n"
                + String.format("You now have %d tasks.", this.taskList.getCurrCapacity());
        return status;
    }
}