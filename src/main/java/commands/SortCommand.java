package commands;

import data.task.TaskList;
import storage.Storage;
import ui.Ui;

public class SortCommand extends Command {

    private TaskList taskList;
    private Storage storage;


    /**
     * Constructs a sort command.
     * @param ui of model.Duke.
     * @param taskList of model.Duke.
     * @param storage of model.Duke.
     */
    public SortCommand(Ui ui, TaskList taskList, Storage storage) {
        super(ui);
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public String execute() {
        this.taskList.sortTaskListAlphabetically();
        this.storage.saveTaskList(this.taskList);
        return this.ui.showSorted() + Ui.showBreakLine() + this.ui.showTaskList(this.taskList.getTaskList());
    }
}
