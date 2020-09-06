package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.task.ToDo;

/**
 * represents a command to add a to do task
 */
public class ToDoCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }
}
