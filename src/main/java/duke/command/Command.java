package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Tasks;

public abstract class Command {
    private CommandType commandType;
    
    public boolean isExit() {
        return commandType == CommandType.BYE;
    }
    
    public abstract void execute(Tasks tasks, Ui ui, Storage storage) throws DukeException;
}
