package duke.command;

import duke.exceptions.DukeLoadingErrorException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    /**
     * Class constructor.
     */
    public ClearCommand() {
        super("clear");
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage) throws DukeLoadingErrorException {
        tasks.clearTasks();
        storage.save(tasks.getTasks());
        return new CommandResult(output.printClearTasksWindow());
    }

}
