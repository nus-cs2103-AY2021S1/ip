package duke.command;

import java.util.LinkedList;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Command that undo previous reversible command.
 */
public class UndoCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Undoes a previously executed command.
     * @param taskList  task list to perform actions on.
     * @param ui        ui object to handle IO.
     * @param storage   permanent storage of data.
     * @param reversibleCommands    the list of commands that can be "undoed".
     * @return  the response of this undo operation.
     * @throws DukeException    if there are no more steps to undo.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage,
                          LinkedList<ReversibleCommand> reversibleCommands) throws DukeException {
        ReversibleCommand latestCommand = reversibleCommands.pollLast();
        if (latestCommand == null) {
            throw new DukeException("There are no more steps I can undo.");
        }

        String response = latestCommand.undo(taskList, ui, storage);
        return response;
    }
}
