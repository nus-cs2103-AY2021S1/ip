package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * represents a command when the input of the user is invalid
 */
public class InvalidCommand extends Command {

    /**
     * class constructor
     */
    public InvalidCommand() {
        super("");
        this.isExit = false;
    }

    /**
     * returns a message indicating that the given command cannot be understood
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return message indicating that the given command cannot be understood
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult(Output.invalidCommandMessage());
    }
}
