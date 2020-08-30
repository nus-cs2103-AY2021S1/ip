package duke.command;

import duke.exception.DukeInvalidListNumberInputException;
import duke.exception.DukeLoadingErrorException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.task.TaskList;

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
            throws DukeInvalidListNumberInputException, DukeLoadingErrorException {
        String response = output.printDoneTaskChatWindow(tasks.completeTask(input));
        storage.save(tasks.getTasks());
        return new CommandResult(response);
    }

}
