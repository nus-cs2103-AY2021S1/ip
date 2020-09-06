package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    /** The task to be added. */
    private Task task;

    /**
     * Creates a new add command with the specified task to be added.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the specified task list.
     *
     * @param tasks The task list the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing an add command.
     * @throws DukeException If there was a problem with executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        assert !this.isExit() : "Add command should not be an exit command.";
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return new CommandResponse(Ui.respondAddTask(task, tasks), this.isExit());
    }
}
