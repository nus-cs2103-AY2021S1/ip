package duke.command;

import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;
import duke.main.TaskList;
import duke.task.Task;

/**
 * Abstraction for an operation involving adding of tasks to a list of task.
 */
public abstract class AddTaskCommand extends Command {
    private static final String MESSAGE_HEAD = "Got it, I have added this task:\n";
    private static final String MESSAGE_MIDDLE = "\nYou now have ";
    private static final String MESSAGE_END = " task(s) in this list";

    protected final String details;

    AddTaskCommand(String details, TaskList taskList) {
        super(taskList);
        this.details = details;
    }

    /**
     * Converts the given String of details into a task to be added to the TaskList.
     *
     * @return Newly created task.
     * @throws MissingDateException If date is not provided (if applicable).
     * @throws InvalidDateException If date provided is in the wrong format (if applicable).
     */
    protected abstract Task getTask() throws MissingDateException, InvalidDateException;

    /**
     * Adds a task to the TaskList and returns a response.
     *
     * @return Message detailing outcome of the task addition operation.
     */
    @Override
    public String execute() {
        try {
            Task task = getTask();
            taskList.add(task);
            return MESSAGE_HEAD + task + MESSAGE_MIDDLE
                    + taskList.size() + MESSAGE_END;
        } catch (InvalidDateException | MissingDateException e) {
            return e.toString();
        }
    }
}
