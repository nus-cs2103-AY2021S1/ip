package duke.command;

import duke.exception.DukeInvalidListNumberInputException;
import duke.exception.DukeLoadingErrorException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Class constructor.
     *
     * @param input A string representing the user input.
     */
    public DeleteCommand(String input) {
        super("delete", input);
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage)
            throws DukeInvalidListNumberInputException, DukeLoadingErrorException {
        String response = output.printDeleteTaskChatWindow(tasks.deleteTask(input), tasks.getTasksSize());
        storage.save(tasks.getTasks());
        return new CommandResult(response);
    }

}
