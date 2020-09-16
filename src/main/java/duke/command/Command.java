package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    protected CommandType commandType;

    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }
    
    public boolean isExitCommand() {
        return commandType.equals(CommandType.EXIT);
    }

    public abstract String execute(Ui ui, TaskList taskList) throws DukeException;
}
