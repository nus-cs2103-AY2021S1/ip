package commands;

import data.task.TaskList;
import ui.Ui;

/**
 * Prints the task list currently stored in Duke.
 */
public class ListCommand extends Command{

    private Ui ui;
    private TaskList taskList;

    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }
    @Override
    public void execute() {
        this.ui.showTaskList(this.taskList.getTaskList());
    }

    @Override
    public String toString() {
        return "ListCommand";
    }
}
