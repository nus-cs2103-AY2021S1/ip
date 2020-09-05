package command;

import task.Task;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents the list command. The list command lists all the current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command. The execution involves listing all the tasks in the task list and
     * printing the relevant UI.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     * @return String response by the application after executing the command.
     */
    public String execute(TaskList lst, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        result.append(ui.showListStatement());
        for (int i = 0; i < lst.size(); i++) {
            int taskNum = i + 1;
            Task task = lst.get(i);
            result.append(ui.showTask(task, taskNum));
        }
        return result.toString();
    }
}
