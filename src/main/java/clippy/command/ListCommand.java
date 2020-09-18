package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class ListCommand extends Command {
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
