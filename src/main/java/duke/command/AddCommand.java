package duke.command;

import duke.*;
import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new add command to add a task.
     * @param task is the task to be add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     * @param tasks is the task list that the command will execute with.
     * @param ui is the ui that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ui.printAddTask(task, tasks);
        storage.save(tasks.getTasks());
    }
}