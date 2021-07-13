package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command that will add a task into the task list.
 */
public class AddCommand extends Command {
    /** The task to be added */
    private final Task task;

    /**
     * Creates a new Add Command with the specified task to be added.
     *
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task into the task list.
     * Does not add the task into the list if a duplicate is detected.
     *
     * @param tasks the task list.
     * @param ui the ui that will generate the added message.
     * @param storage the storage where the tasks will be saved.
     * @return the ui-generated message.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.hasDuplicate(task)) {
            return ui.generateDuplicateMessage(task);
        }
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.generateAddedMessage(task, tasks);
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
