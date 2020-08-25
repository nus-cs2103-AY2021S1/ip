package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Exceptions.IncorrectDoneInputException;
import Exceptions.InvalidDoneFormatException;
import Exceptions.TaskCompletedException;

public class DoneCommand extends Command {
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

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

    public int convertToNumber(String number, int taskSize) throws IncorrectDoneInputException{
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e) {
            throw new IncorrectDoneInputException(taskSize);
        }
    }
}
