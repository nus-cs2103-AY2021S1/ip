package duke.command;

import duke.storage.Storage;

import duke.tasklist.TaskList;

import duke.ui.Ui;

/**
 * Shows all tasks in the TaskList object.
 */
public class ShowCommand extends Command {

    /**
     * Shows all tasks in the TaskList object iff task list is not empty.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.emptyTaskList();
        } else {
            ui.showTaskList(tasks, "");
        }
    }
}
