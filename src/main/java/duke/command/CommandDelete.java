package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.IllegalDoneArgument;
import duke.task.Task;

/**
 * The command to delete a specified task from a designated task list.
 */
public class CommandDelete implements Command {
    private int index;

    /**
     * Construct a new command to delete a task at specified index.
     * @param index the index (can be invalid) of the task to be deleted
     */
    public CommandDelete(int index) {
        this.index = index;
    }

    /**
     * Execute the command to delete the task indexed <code>n</code> from a given list of tasks.
     * @param tasks the <code>TaskList</code> to delete from
     * @param ui to handle interface updates
     * @throws Exception throw when the index <code>n</code> is out of range
     */
    public void execute(TaskList tasks, Ui ui) throws Exception {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalDoneArgument();
        }
        Task deleted = tasks.getList().get(index);
        tasks.remove(index);
        ui.printLine("Noted! I've removed this task:");
        ui.printLine(deleted.toString());
        ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Return false since the command is not to exit.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }
}
