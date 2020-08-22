package command;

import exception.DukeException;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public abstract class Command {
    protected String[] splitCommand;

    public Command(String[] splitCommand) {
        this.splitCommand = splitCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
