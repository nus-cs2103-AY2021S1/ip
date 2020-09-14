package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("Bye! See you next time :)");
    }
}
