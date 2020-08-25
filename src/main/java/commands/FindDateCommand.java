package commands;

import data.exception.DukeException;
import data.exception.DukeInvalidUserInputException;
import data.task.Task;
import data.task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Finds tasks based on their date that matches a specific user_input date.
 */
public class FindDateCommand extends Command{

    private TaskList taskList;
    private Ui ui;
    private String user_input;

    public FindDateCommand(TaskList taskList, Ui ui, String user_input) {
        this.taskList = taskList;
        this.ui = ui;
        this.user_input = user_input;
    }

    @Override
    public void execute() throws DukeException {
        if (this.user_input.trim().length() == 6) {
            this.ui.showFindResults(this.taskList.getTaskList(), ""); //show all tasks
        } else {
            String[] userInputArr = this.user_input.split(" ");
            if (userInputArr.length > 2) {
                throw new DukeInvalidUserInputException("My deepest apologies but it appears the input "
                        + "date is of the wrong format. (Format: YYYY-MM-DD)");
            } else {
                String keyword = userInputArr[1];
                if (keyword.split("-").length != 3) {
                    throw new DukeInvalidUserInputException("My deepest apologies but it appears the input "
                            + "date is of the wrong format. (Format: YYYY-MM-DD)");
                } else {
                    ArrayList<Task> result = this.taskList.findTasksDate(keyword);
                    this.ui.showFindResults(result, keyword);
                }
            }
        }
    }
}