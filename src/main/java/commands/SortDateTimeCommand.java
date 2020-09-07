package commands;

import data.task.TaskList;
import storage.Storage;
import ui.Ui;

public class SortDateTimeCommand extends Command {

    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a sortdatetime command.
     * @param ui of Duke.
     * @param taskList of Duke.
     * @param storage of Duke.
     */
    public SortDateTimeCommand(Ui ui, TaskList taskList, Storage storage) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() {
        this.taskList.sortTaskListDateTime();
        this.storage.saveTaskList(this.taskList);
        return this.ui.showSortedDateTime() + Ui.showBreakLine() + this.ui.showTaskList(this.taskList.getTaskList());
    }
}
