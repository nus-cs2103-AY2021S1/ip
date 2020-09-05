package duke.command;

import duke.exception.InvalidDateException;
import duke.exception.MissingDateException;
import duke.main.TaskList;
import duke.task.Event;
import duke.task.Task;

/**
 * Abstraction for an operation involving adding of events to a list of task.
 */
public class AddEventCommand extends AddTaskCommand {
    /**
     * Constructs a new AddEventCommand object.
     *
     * @param details  Description of Event to create.
     * @param taskList TaskList to be operated on.
     */
    public AddEventCommand(String details, TaskList taskList) {
        super(details, taskList);
    }

    /**
     * Converts the given String of details into an Event to be added to the TaskList.
     *
     * @return Newly created Event.
     * @throws MissingDateException If date is not provided.
     * @throws InvalidDateException If date provided is in the wrong format.
     */
    @Override
    protected Task getTask() throws MissingDateException, InvalidDateException {
        return Event.create(details);
    }
}
