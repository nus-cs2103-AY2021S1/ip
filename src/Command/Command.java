package Command;

import Exception.DukeException;

import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public abstract class Command {
    public String[] command;

    public Command(String[] command) {
        this.command = command;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
