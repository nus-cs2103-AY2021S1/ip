package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

/**
 * Represents a command to list all tasks in the TaskList when executed.
 */
public class ListCommand extends Command {
    /**
     * Returns a numbered list of tasks to be displayed by GUI.
     * Executes the command.
     *
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return A numbered list of tasks to be displayed by GUI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output;
        int numOfTasks = tasks.size();
        assert numOfTasks >= 0 : "Negative number of tasks in Clippy.TaskList";

        if (tasks.isEmpty()) {
            output = ui.showListNoTasks();
        } else {
            output = ui.showListWithTasksHeader() + "\n";
            for (int i = 1; i <= numOfTasks; i++) {
                output += ui.showTaskWithIndex(i, tasks.getTask(i)) + "\n";
            }
        }
        
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ListCommand) {
            return true;
        } else {
            return false;
        }
    }
}
