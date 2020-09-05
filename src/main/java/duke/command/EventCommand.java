package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.Event;

/**
 * represents a command to add an event task
 */
public class EventCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new event task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns a message indicating that the operation was successful
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating the addition of the event task was successful
     * @throws EmptyDescriptionException if the description for the deadline task is empty
     * @throws EmptyDateException if the date for the deadline task is empty
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, EmptyDateException {
        Event event = new Event(fullCommand);
        tasks.add(event);
        storage.save(tasks);
        return Output.addedTaskMessage(event, tasks);
    }
}
