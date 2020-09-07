package butler.command;

import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

/**
 * Represents a command to print out a list of <code>Task</code>.
 */
public class PrintCommand extends Command {

    /**
     * Constructs a command to print out a list of <code>Task</code>.
     */
    public PrintCommand() {
    }

    /**
     * Prints out the list of tasks in <code>taskList</code> using the user interface <code>ui</code>.
     *
     * @param taskList List of tasks on which this command acts on.
     * @param ui User interface to interact with user.
     * @param storage Storage which stores given <code>taskList</code> on hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printTaskList(taskList);
    }

    /**
     * Returns a boolean whether this command is an <code>ExitCommand</code>.
     *
     * @return <code>false</code>
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
