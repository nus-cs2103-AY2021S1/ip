package duke.command;

import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Responsible for executing a bye command.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand.
     */
    public ByeCommand() {
        super(false);
    }

    /**
     * Executes a bye command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Bye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert(!this.shouldLoop());
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public String toString() {
        return "bye";
    }
}
