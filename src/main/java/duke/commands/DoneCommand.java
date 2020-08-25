package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that marks a task in the taskList as done. */
public class DoneCommand extends  Command {

    /** The index of the task to be marked as done. */
    public int num;

    /** Constructor.
     *
     * @param num The index of the task to be marked as done.
     */
    public DoneCommand(int num) {
        this.num = num;
    }

    /** Marks the task as done,
     * prints out the DoneCommand message in Duke format and
     * saves the list in the hard disk.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTaskAsDone(num);
        ui.formatMarkAsDone(taskList.tasks, num);
        storage.saveTaskList(taskList.tasks);
    }
}
