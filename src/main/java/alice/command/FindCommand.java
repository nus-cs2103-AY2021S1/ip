package alice.command;

import java.util.List;

import alice.command.result.CommandResult;
import alice.command.result.FindCommandResult;
import alice.storage.StorageFile;
import alice.task.TaskList;

/**
 * Represents the command to find specific tasks in ALICE.
 */
public class FindCommand implements Command {
    protected static final List<String> NAMES = List.of("find", "search");
    protected static final String DESCRIPTION = "Find tasks using keywords";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <keyword(s)>";

    private final String[] keywords;

    /**
     * Creates a new command to search for tasks based on keywords.
     *
     * @param keywords the list of keywords to search against.
     */
    private FindCommand(String... keywords) {
        this.keywords = keywords;

        assert keywords.length != 0 : "Cannot execute FindCommand with not keywords";
    }

    /**
     * Checks if the command word triggers the {@code FindCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code FindCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * Creates a new Find command to search for tasks based on keywords given by the user.
     *
     * @param argument the string of keywords provided by user
     * @return the {@code FindCommand} with the user's keywords
     * @throws InvalidCommandException if the keywords provided is an empty string
     */
    public static FindCommand createCommand(String argument) throws InvalidCommandException {
        if (!argument.isBlank()) {
            return new FindCommand(argument.strip().split(" "));
        } else {
            throw new InvalidCommandException("The keyword for find cannot be left empty.");
        }
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        String output = tasks.find(keywords);
        if (output == null) {
            return new FindCommandResult("There are no tasks matching your search.", true);
        } else {
            return new FindCommandResult("Here, I found some tasks matching your search:\n" + output, true);
        }
    }
}
