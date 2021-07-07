package command;

import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import ui.Ui;

/**
 * Represents a <code>Command</code> whose task is displaying all of the <code>Task</code>.
 * The <code>TaskListCommand</code> object contains an array of <code>String</code> which is an array
 * containing a command and the argument of the command (if any).
 */
public class TaskListCommand extends Command {

    public TaskListCommand(String[] splitCommand) {
        super(splitCommand);
    }

    /**
     * Displays all <code>Task</code> in <code>tasks</code>.
     *
     * @param tasks  Task list of the Duke.
     * @param ui Ui of the Duke.
     * @param storage Storage of the Duke.
     * @return The response text.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayTasks(tasks);
    }

    /**
     * Returns false to indicate not to exit the Duke.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskListCommand) {
            TaskListCommand other = (TaskListCommand) o;
            return Arrays.equals(other.splitCommand, this.splitCommand);
        } else {
            return false;
        }
    }
}
