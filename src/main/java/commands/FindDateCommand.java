package commands;

import java.util.ArrayList;

import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import ui.Ui;
/**
 * Finds tasks based on their date that matches a specific user_input date.
 */
public class FindDateCommand extends Command {

    private TaskList taskList;
    private String userInput;

    /**
     * Constructs find date command.
     * @param taskList of model.Duke.
     * @param ui of model.Duke.
     * @param userInput details of command.
     */
    public FindDateCommand(TaskList taskList, Ui ui, String userInput) {
        super(ui);
        this.taskList = taskList;
        this.userInput = userInput;
    }

    @Override
    public String execute() throws DukeException {
        if (isFindFieldEmpty(this.userInput)) {
            return this.ui.showFindResults(this.taskList.getTaskList(), ""); //show all tasks
        }
        String[] userInputArr = this.userInput.split(" ");
        if (!isSingleField(userInputArr)) {
            throw new DukeInvalidUserInputException("My deepest apologies but I'm only able to "
                    + "find tasks based on a single date and time.");
        }
        String dateTime = userInputArr[1];
        if (!isCorrectDateTimeFormat(dateTime)) {
            throw new DukeInvalidUserInputException("My deepest apologies but it appears the input "
                    + "date is of the wrong format. (Format: YYYY-MM-DD)");
        }
        ArrayList<Task> result = this.taskList.findTasksDate(dateTime);
        return this.ui.showFindResults(result, dateTime);
    }

    private boolean isFindFieldEmpty(String userInput) {
        return userInput.trim().length() == 6;
    }

    private boolean isSingleField(String[] userInputArr) {
        return userInputArr.length <= 2;
    }

    private boolean isCorrectDateTimeFormat(String dateTime) {
        return dateTime.split("-").length == 3;
    }
}
