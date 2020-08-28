package command;

import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents the list command. The list command lists all the current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command. The execution involves listing all the tasks in the task list and printing the relevant UI.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     */
    public void execute(TaskList lst, Ui ui, Storage storage) {
        ui.showListStatement();
        for (int i = 0; i < lst.size(); i++) {
            int num = i + 1;
            ui.showTask(lst.get(i), num);
        }
        ui.showLine();
    }
}
