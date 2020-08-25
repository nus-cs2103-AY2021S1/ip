package duke.command;

import duke.Storage;
import duke.exception.DoneException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represent a "Done" command
 * A <code>DoneCommand</code> object corresponds to a command of an input "done"
 * followed by the index of the task in which is completed
 */
public class DoneCommand extends Command {

    int index;

    /**
     * Constructor of the DoneCommand Class
     *
     * @param index The index of task that has been completed.
     */
    public DoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Retrieve task from taskList and marks the task as completed
     * Prints corresponding messages and replace the old task in the datafile
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(index);
            task.markAsDone();
            ui.print("Nice! I've marked this task as done:");
            ui.print(task.toString());
            storage.replace(task);
        } catch (DoneException e) {
            ui.print(e.getMessage());
        }
    }
}
