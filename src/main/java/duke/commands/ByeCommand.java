package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command for Mrs Dino to exit.
 */
public class ByeCommand extends Command {
    /**
     * Whether this command is a terminal command.
     */
    private final boolean HAS_FINISHED = true;

    @Override
    public CommandResult execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        return new CommandResult("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return this.HAS_FINISHED;
    }
}
