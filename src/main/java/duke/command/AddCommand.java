package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to add a Task into TaskList.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Creates an AddCommand with Task given.
     *
     * @param newTask Task to be added.
     */
    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    /**
     * Adds a new Task.
     * Displays task added message to user.
     *
     * @param ui Ui object to print messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        tasks.add(this.newTask);
        storage.addTask(this.newTask);
        ui.taskAdded(newTask, tasks);
    }
}
