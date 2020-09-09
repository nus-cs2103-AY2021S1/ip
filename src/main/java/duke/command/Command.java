package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    protected boolean isExitCommand = false;

    public Command(boolean isExitCommand) {
        this.isExitCommand = isExitCommand;
    }

    public boolean isExitCommand() {
        return this.isExitCommand;
    }

    public abstract void execute(Ui ui, TaskList taskList) throws DukeException;
}
