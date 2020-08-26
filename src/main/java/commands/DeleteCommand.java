package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.IncorrectDeleteInputException;
import exceptions.IncorrectDoneInputException;
import exceptions.InvalidDeleteFormatException;

import tasks.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidDeleteFormatException,
            IncorrectDoneInputException, IncorrectDeleteInputException {
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
        ui.deleteMessage(deletedTask.toString(), taskList.getTaskListLength());
    }

    public int convertToNumber(String number, int taskSize) throws IncorrectDoneInputException{
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e) {
            throw new IncorrectDoneInputException(taskSize);
        }
    }
}
