package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private static int taskindex;

    /**
     * Constructor for a new DeleteCommand object
     *
     * @param taskindex index of task in task list to be deleted
     */
    public DeleteCommand(int taskindex) {
        DeleteCommand.taskindex = taskindex - 1;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks
     * @param ui object to output messages
     * @param storage object to write TaskList to file
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Ui.display("Noted. I've removed this task:\n   "
                    + tasks.getTask(taskindex)
                    + "\nNow you have "
                    + tasks.getSize()
                    + " tasks in your list.");
            tasks.deleteTask(taskindex);
            Storage.writeToFile(tasks.getList());
        } catch (Exception e) {
            throw new DukeException("This task does not exist!");
        }
    }
}
