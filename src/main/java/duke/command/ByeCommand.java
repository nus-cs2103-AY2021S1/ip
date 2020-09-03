package duke.command;

import duke.messages.Output;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Represents a command to exit the chat bot.
 */
public class ByeCommand extends Command {

    /**
     * Class constructor.
     */
    public ByeCommand() {
        super("bye");
    }

    @Override
    public CommandResult execute(TaskList tasks, Output output, Storage storage) {
        return new CommandResult(output.printGoodbye(), true);
    }

}
