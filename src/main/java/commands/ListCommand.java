package commands;

import data.task.TaskList;
import ui.Ui;

/**
 * Prints the task list currently stored in model.Duke.
 */
public class ListCommand extends Command {

    private TaskList taskList;

    /**
     * Constructs a list command.
     * @param ui of model.Duke.
     * @param taskList of model.Duke.
     */
    public ListCommand(Ui ui, TaskList taskList) {
        super(ui);
        this.ui = ui;
        this.taskList = taskList;
    }
    @Override
    public String execute() {
        return this.ui.showTaskList(this.taskList.getTaskList());
    }

    @Override
    public String toString() {
        return "ListCommand";
    }
}
