package commands;

import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Finds tasks based on their description that matches a specific user_input keyword.
 */
public class FindCommand extends Command{

    private TaskList taskList;
    private Ui ui;
    private String user_input;

    public FindCommand(TaskList taskList, Ui ui, String user_input) {
        this.taskList = taskList;
        this.ui = ui;
        this.user_input = user_input;
    }


    @Override
    public void execute() throws DukeException {
        if (this.user_input.trim().length() == 4) {
            this.ui.showFindResults(this.taskList.getTaskList(), ""); //show all tasks
        } else {
            String[] userInputArr = this.user_input.split(" ");
            if (userInputArr.length > 2) {
                throw new DukeInvalidUserInputException("My deepest apologies but I'm only able to "
                        + "find tasks based on a single keyword.");
            } else {
                String keyword = userInputArr[1];
                ArrayList<Task> result = this.taskList.findTasksKeyword(keyword);
                this.ui.showFindResults(result, keyword);
            }
        }
    }
}