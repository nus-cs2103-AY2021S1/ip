package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Inherits from generic command class.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Undo the previous command, "
        + "but cannot undo an undo command.\n\n"
        + "Example: " + COMMAND_WORD;
    /**
     * Reverts the previous command given by the user.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     * @throws DukeException If there are no more previous commands to revert.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) throws DukeException {
        handler.retrievePreviousTaskList();
        System.out.println();
        handler.printList();
        storage.saveToFile(handler.getTasks());
    }
}
