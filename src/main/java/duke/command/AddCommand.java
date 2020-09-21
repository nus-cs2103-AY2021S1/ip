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
     * Creates an AddCommand with given Task.
     *
     * @param newTask Task to be added.
     */
    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    /**
     * Adds a new Task.
     * Displays Task added message to user.
     *
     * @param ui Ui object to display messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of Tasks.
     * @return Ui message to indicate Task has been added.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        tasks.add(this.newTask);
        storage.addTask(this.newTask);
        return ui.taskAdded(newTask, tasks);
    }

}
