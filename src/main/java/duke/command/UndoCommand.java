package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Inherits from generic command class.
 */
public class UndoCommand extends Command {

    /**
     * Reverts the previous command given by the user.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     * @throws DukeException If there are no more previous commands to revert.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) throws DukeException {
        handler.retrievePreviousTaskList();
        System.out.println();
        handler.printList();
        storage.saveToFile(handler.getTasks());
    }
}
