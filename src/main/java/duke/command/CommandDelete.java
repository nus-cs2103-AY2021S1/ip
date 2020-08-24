package duke.command;

import duke.*;
import duke.task.*;
import duke.exception.*;

/**
 * The command to delete a specified task from a designated task list.
 */
public class CommandDelete implements Command {
    private int n;

    /**
     * Construct a new command to delete a task at specified index.
     * @param n the index (can be invalid) of the task to be deleted
     */
    public CommandDelete(int n) {
        this.n = n;
    }

    /**
     * Execute the command to delete the task indexed <code>n</code> from a given list of tasks.
     * @param tasks the <code>TaskList</code> to delete from
     * @param ui to handle interface updates
     * @throws Exception throw when the index <code>n</code> is out of range
     */
    public void execute(TaskList tasks, Ui ui) throws Exception {
        if (n < 0 || n >= tasks.size()) {
            throw new IllegalDoneArgument();
        }
        Task deleted = tasks.getList().get(n);
        tasks.remove(n);
        ui.printLine("Noted! I've removed this task:");
        ui.printLine(deleted.toString());
        ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Return false since the command is not to exit.
     * @return <code>false</code>
     */
    public boolean isExit() { return false; }
}
