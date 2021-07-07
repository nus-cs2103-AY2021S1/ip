package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidFindFormatException;

/**
 * Class to initiate the find command.
 */
public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the find command, and finds the items in the task list, then prints
     * the matching task list, if there are no errors in input.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to read or save the task list in the hardware.
     * @return String message of the command.
     * @throws InvalidFindFormatException If the format of find command is wrong.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage)
            throws InvalidFindFormatException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length == 1) {
            throw new InvalidFindFormatException();
        }

        String keyword = fullCommand.substring(5).trim();

        TaskList matchingTaskList = taskList.findTask(keyword);

        return ui.findTaskMessage(matchingTaskList.toString(), matchingTaskList.getTaskListLength());
    }
}
