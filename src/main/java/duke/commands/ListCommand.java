package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for the logic of listing tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the full list of tasks added.\n"
            + "Example: " + COMMAND_WORD + "\n";

    /**
     * Collates the list of tasks in the task list.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult containing a list of all tasks.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = "";
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            message += (i + 1) + ": " + tasks.getTask(i) + "\n";
        }
        return new CommandResult(message);
    }
}
