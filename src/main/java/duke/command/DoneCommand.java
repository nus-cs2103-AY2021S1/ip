package duke.command;

import duke.*;
import duke.exception.*;

/**
 * DoneCommand class for when Done command is prompted by User
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor for DoneCommand.
     * @param index index of tasking in the list for which to be completed.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Execution command for Done
     * @param tasklist list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeIndexException When the input index does not match the list.
     */
    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeIndexException {

        if (index > tasklist.getTaskSize() - 1 || index < 0) {
            String errorMessage = "Wrong list number input. " +
                    "Please put a number between 1 and " + tasklist.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        tasklist.makeTaskDone(index);
        ui.printDone(tasklist.get(index).toString());

    }
}
