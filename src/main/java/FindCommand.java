import java.util.ArrayList;

/**
 * Finds a task based on the given keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    FindCommand(String arguments) {
        super(arguments);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        ArrayList<Task> currentTasks = tasks.getTasks();

        for (Task t : currentTasks) {
            String description = t.getDescription().toLowerCase();
            String arg = arguments.toLowerCase();
            if (description.contains(arg)) {
                matchingTasks.add(t);
            }
        }

        int size = matchingTasks.size();
        String message;

        if (size == 0) {
            message = "There is no matching task in your list.\n";
        } else if (size == 1) {
            message = "Here is the matching task in your list:\n";
        } else {
            message = "Here are the matching tasks in your list:\n";
        }

        for (int i = 0; i < matchingTasks.size(); i++) {
            message += String.format("%d. %s%n", i + 1, matchingTasks.get(i));
        }

        return new CommandResult(message);
    }
}
