package duke.operation;

import duke.task.TaskList;

/**
 * The operation that lists all the Tasks in TaskList.
 */
public class ListOperation extends Operation {
    private final TaskList taskList;

    /**
     * Constructor method.
     * @param taskList the TaskList that is to be printed on.
     */
    public ListOperation(TaskList taskList) {
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
     * Executes the List Operation by retrieving all Tasks in TaskList.
     * @return a String containing all Tasks stored in TaskList.
     */
    @Override
    public String execute() {
        return "Here are your tasks:\n" + this.taskList.toString();
    }
}
