package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD + "\n";

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(DoneCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE
                + "\n" + FindCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + ReminderCommand.MESSAGE_USAGE
                + "\n" + Ui.DIVIDER
                + "\n" + "Commands to create new Tasks..."
                + "\n" + DeadlineCommand.MESSAGE_USAGE
                + "\n" + EventCommand.MESSAGE_USAGE
                + "\n" + ToDoCommand.MESSAGE_USAGE
        );
    }
}
