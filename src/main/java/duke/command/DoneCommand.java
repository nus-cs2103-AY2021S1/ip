package duke.command;

import duke.exceptions.DukeInvalidIndexException;
import duke.exceptions.DukeLoadingErrorException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to complete a task.
 */
public class DoneCommand extends Command {

    /**
     * Class constructor.
     *
     * @param input A string representing the user input.
     */
    public DoneCommand(String input) {
        super("done", input);
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage)
            throws DukeInvalidIndexException, DukeLoadingErrorException {
        String response = output.printDoneTaskChatWindow(tasks.completeTask(input));
        storage.save(tasks.getTasks());
        return new CommandResult(response);
    }

}
