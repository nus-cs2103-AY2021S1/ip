public class ExitCommand extends Command {
    public final static String COMMAND_WORD = "bye";

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
