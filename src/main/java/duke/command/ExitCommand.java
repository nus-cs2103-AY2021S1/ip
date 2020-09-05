package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

import java.util.Arrays;

public class ExitCommand extends Command {
    private CommandType commandType;

    public ExitCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
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