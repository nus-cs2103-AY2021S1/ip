package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the command that adds a task to the taskList when executed. */
public class AddCommand extends Command {

    /** The task to be added. */
    private Task task;

    /** Constructs a new AddCommand object with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /** Adds the task to the taskList,
     * prints out the AddCommand message in Duke format and
     * saves the list in the hard disk.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert task != null : "Oh no! The task added should not be empty.";
        taskList.addTask(task);
        setDialog(ui.formatAddTask(taskList.getTasks(), task));
        storage.saveTaskList(taskList.getTasks());
    }
}
