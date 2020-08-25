/**
 * Lists out the tasks that user has added.
 */
public class ListCommand extends Command {
    public final static String COMMAND_WORD = "list";

    ListCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = tasks.toString();
        return new CommandResult(message);
    }
}
