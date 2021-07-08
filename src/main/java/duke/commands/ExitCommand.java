package src.main.java.duke.commands;


/**
 * Represents an exit command that helps to terminate the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Saving files and exiting Best2103/TBot as requested ...";

    @Override
    public CommandResult execute() {
        System.exit(0);
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
