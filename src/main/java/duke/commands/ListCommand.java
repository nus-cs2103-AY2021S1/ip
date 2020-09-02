package duke.commands;


import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the list command to display all tasks in task list.
 */
public class ListCommand extends Command {

    /**
     * Lists tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
