package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.exception.EmptyDateException;
import duke.exception.EmptyDescriptionException;
import duke.task.Deadline;

/**
 * represents a command to add a deadline task
 */
public class DeadlineCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }
}
