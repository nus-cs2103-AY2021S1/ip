package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidByeCommandException;

/**
 * Class to initiate the bye command.
 */
public class ByeCommand extends Command {

    public ByeCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the bye command, and sends a signal to exit the duke program,
     * if there are no errors in the input.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to read or save the task list in the hardware.
     * @return String message of the command.
     * @throws InvalidByeCommandException If the format of bye command is wrong.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage)
            throws InvalidByeCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 1) {
            throw new InvalidByeCommandException();
        }
        return ui.byeMessage();
    }
}
