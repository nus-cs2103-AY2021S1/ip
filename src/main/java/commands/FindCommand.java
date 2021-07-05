package commands;

import java.util.ArrayList;

import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import ui.Ui;

/**
 * Finds tasks based on their description that matches a specific userInput keyword.
 */
public class FindCommand extends Command {

    private TaskList taskList;
    private String userInput;

    /**
     * Constructs a find command.
     * @param taskList of model.Duke.
     * @param ui of model.Duke.
     * @param userInput details of command.
     */
    public FindCommand(TaskList taskList, Ui ui, String userInput) {
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
                    + "find tasks based on a single keyword.");
        }
        String keyword = userInputArr[1];
        ArrayList<Task> result = this.taskList.findTasksKeyword(keyword);
        return this.ui.showFindResults(result, keyword);
    }

    private boolean isFindFieldEmpty(String userInput) {
        return userInput.trim().length() == 4;
    }

    private boolean isSingleField(String[] userInputArr) {
        return userInputArr.length <= 2;
    }
}
