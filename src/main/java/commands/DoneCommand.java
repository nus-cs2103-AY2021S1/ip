package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.*;


/**
 * Class to initiate the done command.
 */
public class DoneCommand extends Command {
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the done command, and deletes the task form the list,
     * if there are no errors in the code.
     *
     * @param taskList Task list which contains the current task.
     * @param ui Ui object to interact with the user.
     * @param storage Storage object to read or save the task list in the hardware.
     * @throws InvalidDoneFormatException If the format of done command is wrong.
     * @throws IncorrectDoneInputException If the string after done command is not a number or does
     * not fall within the valid range.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidDoneFormatException,
            IncorrectDoneInputException, TaskCompletedException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 2) {
            throw new InvalidDoneFormatException();
        }

        int itemIndex = convertToNumber(tempArray[1], taskList.getTaskListLength()) - 1;
        if (itemIndex < 0 || Integer.parseInt(tempArray[1]) > taskList.getTaskListLength()) {
            throw new IncorrectDoneInputException(taskList.getTaskListLength());
        }
        taskList.markTaskAsDone(itemIndex);
        ui.markAsDoneMessage(taskList.getSpecificTask(itemIndex).toString());
    }

    /**
     * Converts a given string to a number.
     * Returns the number in int form.
     *
     * @param number Item to convert to int.
     * @param taskSize Size of the current task list.
     * @throws IncorrectDoneInputException If the string after delete command is not a number or does
     * not fall within the valid range.
     */
    public int convertToNumber(String number, int taskSize) throws IncorrectDoneInputException{
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e) {
            throw new IncorrectDoneInputException(taskSize);
        }
    }
}
