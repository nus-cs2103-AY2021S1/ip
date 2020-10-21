package duke.command;

import duke.Ui;
import duke.Storage;

/**
 * A command that signals the application to close.
 */
public class ExitCommand extends Command {
    private CommandType commandType;

    /**
     * Constructs a new ExitCommand of the specified CommandType.
     *
     * @param commandType The type of the ExitCommand.
     */
    public ExitCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return commandType.toString();
    }
}
