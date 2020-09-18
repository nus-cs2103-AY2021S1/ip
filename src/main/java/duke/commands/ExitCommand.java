package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for the logic of exiting the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD + "\n";

    /**
     * Returns a message indicating the program is exiting.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult noting the program is exiting.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("Bye! See you next time :)");
    }
}
