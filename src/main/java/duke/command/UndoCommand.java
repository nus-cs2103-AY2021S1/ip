package duke.command;

import duke.DukeException;
import duke.History;
import duke.Storage;
import duke.task.TaskList;

public class UndoCommand extends Command {
    private final String userInput;

    /**
     * Creates an Undo Command to revert the state of Duke's TaskList to the previous state.
     *
     * @param fullCommand <code>String</code> of the entire command from the user input.
     */
    public UndoCommand(String fullCommand) {
        super();
        this.userInput = fullCommand;
    }

    /**
     * Changes the state of Duke's TaskList to the previous state.
     *
     * @param taskList the List of all the Tasks that Duke has.
     * @param storage the database of Tasks that is saved to the user's local storage.
     * @param history the state of all changes made to Duke's TaskList.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Storage storage, History history) throws DukeException {
        TaskList previousState = history.undoHistory();
        taskList.replace(previousState);
        storage.undoChanges(previousState);
        return "Undo successful! This is what your list looks like now:\n" + previousState.toString();
    }
}
