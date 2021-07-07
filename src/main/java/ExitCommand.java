/**
 * Command that signals the termination of the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    ExitCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = "Bye. Hope to see you again soon!";
        this.isExit = true;
        return new CommandResult(message);
    }
}
