package duke.command;

import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;
import duke.main.TaskList;
import duke.task.Task;

/**
 * Abstraction for an operation involving adding of tasks to a list of task.
 */
public abstract class AddTaskCommand extends TaskListOperator {
    private static final String MESSAGE_HEAD = "Got it, I have added this task:\n";
    private static final String MESSAGE_MIDDLE = "\nYou now have ";
    private static final String MESSAGE_END = " task(s) in this list";
    private static final String DUPE_DETECTED = "This task already exists! "
            + "Sorry, I don't do duplicates.";

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
     * The task is only added if it did not exist in the task list prior
     * (i.e. for all tasks x in the task list, x.equals(given task) = false).
     *
     * @return Message detailing outcome of the task addition operation.
     */
    @Override
    public String execute() {
        try {
            Task task = getTask();
            if (taskList.contains(task)) {
                return DUPE_DETECTED;
            } else {
                taskList.add(task);
                return MESSAGE_HEAD + task + MESSAGE_MIDDLE
                        + taskList.size() + MESSAGE_END;
            }
        } catch (InvalidDateException | MissingDateException e) {
            return e.toString();
        }
    }
}
