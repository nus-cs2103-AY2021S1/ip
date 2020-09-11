package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getGoodbyeMessage();
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
