package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(
    DeadlineCommand.MESSAGE_USAGE
                + "\n" + DoneCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE
                + "\n" + EventCommand.MESSAGE_USAGE
                + "\n" + FindCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + ToDoCommand.MESSAGE_USAGE
        );
    }
}
