package duke.commands;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\nExits the program.\n"
            + "  Example: " + COMMAND_WORD;

    public static final String MESSAGE_EXIT_ACKNOWLEDGMENT = "Exiting Duke as requested...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGMENT, true);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

}
