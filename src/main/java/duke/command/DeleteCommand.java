package duke.command;

import duke.*;
import duke.exception.*;

/**
 * DeleteCommand class for when delete command is prompted by User
 * @author Kor Ming Soon
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructor for delete command.
     * @param index index of the task belonging in the list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execution command for Delete.
     * @param tasklist list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeIndexException When the input index does not match the list.
     */
    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeIndexException {

        if (this.index > tasklist.getTaskSize() - 1 || this.index < 0) {
            String errorMessage = "Wrong list number input. " +
                    "Please put a number between 1 and " + tasklist.getTaskSize();
            throw new DukeIndexException(errorMessage);
        }

        ui.printDelete(tasklist.get(index).toString(), tasklist.getTaskSize());
        tasklist.removeTask(index);

    }
}
