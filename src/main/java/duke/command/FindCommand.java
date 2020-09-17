package duke.command;

import duke.exceptions.DukeInvalidKeywordException;
import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {

    /**
     * Class constructor.
     *
     * @param input A string representing the user input.
     */
    public FindCommand(String input) {
        super("find", input);
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage) throws DukeInvalidKeywordException {
        return new CommandResult(output.printFindTaskChatWindow(tasks.findTasks(input)));
    }

}
