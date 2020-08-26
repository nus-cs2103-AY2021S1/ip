package duke.command;

import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.exception.DukeIOException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents an request by the user to mark a Task object at the given index from the list of Tasks as completed.
 */
public class DoneCommand extends Command {

    int index;

    /**
     * Constructor for a DoneCommand
     * @param args the input string to be parsed for the index of the list to be marked as complete.
     * @throws DukeArgumentException if the index provided in the input string is invalid.
     */
    public DoneCommand(String args) throws DukeArgumentException {
        try {
            int index = Integer.parseInt(args);
            this.index = index - 1;
        } catch (NumberFormatException ne) {
            throw new DukeArgumentException("Invalid index");
        }
    }

    @Override
    public boolean isExit() {
        return super.isExit();
    }

    /**
     * Attempts to mark the Task object at the index of the Storage object as completed.
     * @param storage The Storage object in which to mark the Task at the index given as complete.
     * @throws DukeExecutionException if the index is out of range, or the program runs into an IOException
     */
    @Override
    public void execute(Storage storage) throws DukeExecutionException {
        try {
            Ui.showTaskCompletion(storage.complete(index));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeExecutionException(String.format("Could not execute command due to the index %d being out " +
                    "of range", index));
        } catch (DukeIOException die) {
            throw new DukeExecutionException("Could not execute command due to IO exception.");
        }

    }
}
