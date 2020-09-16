package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the full list of tasks added.\n"
            + "Example: " + COMMAND_WORD + "\n";
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        String message = "";
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            message += (i + 1) + ": " + tasks.getTask(i) + "\n";
        }
        return new CommandResult(message);
    }
}
