package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command that will add a task into the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new add command with the specified task to be added.
     *
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task into the task list.
     *
     * @param tasks the task list where the task will be added to.
     * @param ui the ui that will display a message when the task has been successfully added.
     * @param storage the storage where the tasks will be saved after adding.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAdded(task, tasks);
        storage.save(tasks.getTasks());
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}