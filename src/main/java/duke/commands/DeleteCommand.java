package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that deletes a task from the taskList when executed. */
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
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.formatDeleteTask(taskList.tasks, num);
        taskList.deleteTask(num);
        storage.saveTaskList(taskList.tasks);
    }
}
