package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Inherits from generic command class.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Displays all tasks and their details.\n"
        + "Example: "
        + COMMAND_WORD;


    /**
     * Prints the task list.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) throws DukeException {
        handler.printList();
    }
}
