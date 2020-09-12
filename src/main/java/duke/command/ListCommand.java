package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command to list out the tasks currently in the task list.
 */
public class ListCommand extends Command {
    private String userInput;

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Lists out the tasks in <code>tasks</code>.
     *
     * @param tasks List of <code>Task</code> objects.
     * @param ui Ui object created by Duke.
     * @param storage Storage object created by Duke.
     * @return Resultant string with all the tasks in the list
     */
    public String executeToString(TaskList tasks, Ui ui, Storage storage) {
        String result = "    Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n    " + (i + 1) + ". " + tasks.get(i);

        }
        return result;
    }
}

