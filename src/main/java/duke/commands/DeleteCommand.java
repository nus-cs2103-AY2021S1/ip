package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for handling task deletion logic.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int index;

    /**
     * Constructor for DeleteCommand
     * @param index index of task to be deleted within the task list.
     */
    public DeleteCommand(int index) {
        this.index = index; // check index > 0 and < tasks.size
    }

    /**
     * Removes the task from the task list and rewrites the storage updating
     * the task list.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult containing the message noting that the
     * task has been deleted.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.deleteTask(index);
        storage.writeAllTasks(tasks);
        return new CommandResult("Task has been deleted");
    }
}
