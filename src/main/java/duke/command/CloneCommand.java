package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class CloneCommand extends Command {
    public static final String COMMAND = "clone";

    private int sourceIndex;
    private int destinationIndex;

    /**
     * Creates a <code>CloneCommand</code> object
     * @param string The source and destination position of the cloned task
     * @throws InvalidIndexException If cannot parse the String to 1 or 2 indices
     */
    public CloneCommand(String string) throws InvalidIndexException {
        String[] splitInput = string.trim().split("\\s+");
        assert splitInput.length > 0 : "split input can have length 0";

        if (splitInput.length > 2) {
            throw new InvalidIndexException();
        }

        if (splitInput.length == 1) {
            try {
                sourceIndex = Integer.parseInt(splitInput[0]) - 1;
                destinationIndex = sourceIndex;
            } catch (NumberFormatException e) {
                throw new InvalidIndexException();
            }
        }

        if (splitInput.length == 2) {
            try {
                sourceIndex = Integer.parseInt(splitInput[0]) - 1;
                destinationIndex = Integer.parseInt(splitInput[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidIndexException();
            }
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        assert tasks != null : "tasklist object cannot be null";
        assert ui != null : "ui object cannot be null";
        try {
            Task sourceTask = tasks.get(sourceIndex);
            Task clonedTask = sourceTask.clone();
            tasks.add(destinationIndex, clonedTask);
            return ui.showCloneSuccess(sourceTask);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}
