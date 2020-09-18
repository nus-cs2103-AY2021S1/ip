package duke.commands;

import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for the logic of finding tasks in the
 * task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a list of tasks matching the search terms.\n"
            + "Example: " + COMMAND_WORD + " CS\n";
    private String searchPhrase;

    /**
     * Constructor initialising the search phrase.
     * @param searchPhrase Keyword for the task the user is searching for.
     */
    public FindCommand(String searchPhrase) {
        assert !searchPhrase.trim().equals("");
        this.searchPhrase = searchPhrase;
    }

    /**
     * Searches the task list for search phrase and
     * returns all tasks containing the search phrase.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult with a message of all tasks
     * that contain the search phrase.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = "Here are the matching tasks in your list\n";
        for (Task task : tasks.getTaskList()) {
            if (task.getDescription().contains(searchPhrase)) {
                message += task.toString() + "\n";
            }
        }
        return new CommandResult(message);
    }
}
