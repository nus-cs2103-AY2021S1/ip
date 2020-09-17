package duke.commands;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\nShows program usage instructions.\n"
            + "  Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult("--- Commands ---\n"
                + "todo, deadline, event, done, delete, find, list, stat, exit\n\n"
                + AddTodoCommand.MESSAGE_USAGE + "\n\n"
                + AddDeadlineCommand.MESSAGE_USAGE + "\n\n"
                + AddEventCommand.MESSAGE_USAGE + "\n\n"
                + DoneCommand.MESSAGE_USAGE + "\n\n"
                + DeleteCommand.MESSAGE_USAGE + "\n\n"
                + FindCommand.MESSAGE_USAGE + "\n\n"
                + ListCommand.MESSAGE_USAGE + "\n\n"
                + StatCommand.MESSAGE_USAGE + "\n\n"
                + ExitCommand.MESSAGE_USAGE
        );
    }

}
