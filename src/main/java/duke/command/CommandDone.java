package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.IllegalDoneArgument;

/**
 * The command to mark a task of given index as done.
 */
public class CommandDone implements Command {
    private int index;

    /**
     * Construct a new command to mark the task at given index as done.
     * @param index the index (can be invalid) of the task to be marked as done
     */
    public CommandDone(int index) {
        this.index = index;
    }

    /**
     * Execute the command to mark the task at given index as done.
     * @param tasks the <code>TaskList</code> to operate on
     * @param ui the <code>Ui</code> object to handle interface updates.
     * @throws Exception throw if the index is out of range
     */
    public void execute(TaskList tasks, Ui ui) throws Exception {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalDoneArgument();
        }
        tasks.markAsDone(index);
        ui.printLine("Nice! I've marked this task as done:");
        ui.printLine(tasks.getList().get(index).toString());
    }

    /**
     * Return <code>false</code> since the command is not to exit.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }
}
