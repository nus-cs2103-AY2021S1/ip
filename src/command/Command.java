package command;

import exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected String[] splitCommand;

    public Command(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
