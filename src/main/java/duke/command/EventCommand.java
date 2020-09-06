package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.Task;
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
}
