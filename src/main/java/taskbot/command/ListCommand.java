package taskbot.command;

import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * Encapsulates a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.listTasks();
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        /* Check if obj is an instance of this class.
           All ListCommand instances are equal.
         */
        return obj instanceof ListCommand;
    }
}
