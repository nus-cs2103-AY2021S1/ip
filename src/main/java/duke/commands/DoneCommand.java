package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for marking a task as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task in the task list as completed.\n"
            + "Example: " + COMMAND_WORD + " 1\n";
    private final int index;

    /**
     * Constructor initialising the index.
     * @param index Index of task to be marked as completed.
     */
    public DoneCommand(int index) {
        this.index = index; // check for index > 0 and < tasks.size
    }

    /**
     * Marks a task in the task list as completed and rewrites the
     * storage to reflect this change.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult containing a message noting that the task
     * has been marked as completed.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.getTask(index).markAsDone();
        storage.writeAllTasks(tasks);
        return new CommandResult("Task is marked as done");
    }
}
