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
    private final Task task;

    /**
     * Creates a new add command to add a task.
     *
     * @param task is the task to be add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     *
     * @param tasks is the task list that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @return a command response after executing the add command.
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        if (tasks.isDuplicate(task)) {
            return new CommandResponse(Ui.printDuplicateTask(), this.isExit());
        }
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return new CommandResponse(Ui.printAddTask(task, tasks), this.isExit());
    }
}
