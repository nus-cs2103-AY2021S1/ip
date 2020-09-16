package commands;

import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.storage.Storage;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays a list of tasks matching the search terms.\n"
            + "Example: " + COMMAND_WORD + " CS\n";
    private String searchPhrase;
    public FindCommand(String searchPhrase) {
        assert !searchPhrase.trim().equals("");
        this.searchPhrase = searchPhrase;
    }
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
