package duke.command;

import duke.*;
import duke.task.*;

/**
 * Command to add a task to a designated list.
 */
public class CommandAdd implements Command {
    private final Task newTask;

    /**
     * Construct a new command to add a specified task.
     * @param newTask the new task to be added by <code>CommandAdd</code>
     */
    public CommandAdd(Task newTask) { this.newTask = newTask; }

    /**
     * Add the new task to a specified <code>TaskList</code> with interface updates.
     * @param tasks the <code>TaskList</code> which the new task will be adding to
     * @param ui the <code>Ui</code> object to handle the interface updates
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
       ui.printLine("Got it. I've added this task:");
       ui.printLine(newTask.toString());
       tasks.add(newTask);
       ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Retrn <code>false</code> since the command is not exit.
     * @return false
     */
    public boolean isExit() { return false; }
}
