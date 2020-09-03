package duke.command;

import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to show the task list.
 */
public class ListCommand extends Command {

    /**
     * Class constructor.
     */
    public ListCommand() {
        super("list");
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage) {
        return new CommandResult(output.printTasksChatWindow(tasks.getTasks()));
    }

}
