package duke.command;

import duke.main.TaskList;

/**
 * Abstraction for the list command.
 */
public class ListCommand extends TaskListOperator {
    private static final String MESSAGE = "Here are the tasks in your list:\n";

    /**
     * Constructs a new ListCommand object.
     *
     * @param taskList TaskList to be operated on.
     */
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Lists all tasks in the TaskList if any.
     *
     * @return List of all tasks.
     */
    @Override
    public String execute() {
        if (taskList.size() == 0) {
            return taskList.toString();
        } else {
            return MESSAGE + taskList.toString();
        }
    }
}
