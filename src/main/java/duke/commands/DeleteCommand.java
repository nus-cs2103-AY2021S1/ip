package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that deletes a task from TaskList's list when executed. */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted. */
    public int num;

    /** Constructor.
     *
     * @param num The index of the task to be deleted.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /** Deletes the task from TaskList's list,
     * prints out the DeleteCommand message in Duke format and
     * saves the list in the hard disk.
     *
     * @param tasks The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the list of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.formatDeleteTask(tasks.lst, num);
        tasks.deleteTask(num);
        storage.saveTaskList(tasks.lst);
    }
}
