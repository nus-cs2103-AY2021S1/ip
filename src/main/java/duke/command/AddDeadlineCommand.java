package duke.command;

import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;
import duke.main.TaskList;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Abstraction for an operation involving adding of deadlines to a list of task.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    /**
     * Constructs a new AddDeadlineCommand object.
     *
     * @param details Description of Deadline to create.
     * @param taskList TaskList to be operated on.
     */
    public AddDeadlineCommand(String details, TaskList taskList) {
        super(details, taskList);
    }

    /**
     * Converts the given String of details into a Deadline to be added to the TaskList.
     *
     * @return Newly created Deadline.
     * @throws MissingDateException If date is not provided.
     * @throws InvalidDateException If date provided is in the wrong format.
     */
    @Override
    protected Task getTask() throws MissingDateException, InvalidDateException {
        return Deadline.create(details);
    }
}
