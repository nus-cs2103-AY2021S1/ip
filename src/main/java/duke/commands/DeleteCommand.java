package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.IncorrectDeleteInputException;
import duke.exceptions.InvalidDeleteFormatException;
import duke.tasks.Task;

/**
 * Class to initiate the delete command.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the delete command, and deletes the task form the list,
     * if there are no errors in the input.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to read or save the task list in the hardware.
     * @return String message of the command.
     * @throws InvalidDeleteFormatException If the format of delete command is wrong.
     * @throws IncorrectDeleteInputException If the string after delete command is not a number or does
     * not fall within the valid range.
     */
    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidDeleteFormatException,
            IncorrectDeleteInputException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 2) {
            throw new InvalidDeleteFormatException();
        }

        int itemIndex = convertToNumber(tempArray[1], taskList.getTaskListLength()) - 1;
        if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > taskList.getTaskListLength()) {
            throw new IncorrectDeleteInputException(taskList.getTaskListLength());
        }
        Task deletedTask = taskList.getSpecificTask(itemIndex);
        taskList.deleteTask(itemIndex);
        return ui.deleteMessage(deletedTask.toString(), taskList.getTaskListLength());
    }

    /**
     * Converts a given string to a number.
     * Returns the number in int form.
     *
     * @param number Item to convert to int.
     * @param taskSize Size of the current task list.
     * @throws IncorrectDeleteInputException If the string after delete command is not a number or does
     * not fall within the valid range.
     */
    public int convertToNumber(String number, int taskSize) throws IncorrectDeleteInputException {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IncorrectDeleteInputException(taskSize);
        }
    }
}
