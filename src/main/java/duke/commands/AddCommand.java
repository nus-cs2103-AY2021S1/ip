package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that adds a task to TaskList's list when executed. */
public class AddCommand extends Command {

    /** The task to be added. */
    public Task task;

    /** Constructor.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /** Adds the task to TaskList's list,
     * prints out the AddCommand message in Duke format and
     * saves the list in the hard disk.
     *
     * @param tasks The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.formatAddTask(tasks.lst, task);
        storage.saveTaskList(tasks.lst);
    }
}
