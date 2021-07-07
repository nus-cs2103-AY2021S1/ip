package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidListCommandException;

/**
 * Class to initiate the list command.
 */
public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the list command, and prints out the list of items in the task list,
     * if there are no errors in the input.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to read or save the task list in the hardware.
     * @return String message of the command.
     * @throws InvalidListCommandException If the format of list command is wrong.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidListCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 1) {
            throw new InvalidListCommandException();
        }
        return ui.listMessage(taskList.toString());
    }
}
