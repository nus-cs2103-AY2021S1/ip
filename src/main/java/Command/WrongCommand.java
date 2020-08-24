package main.java.Command;

import main.java.Exception.AnonymousException;
import main.java.Storage.Storage;
import main.java.Task.TaskList;
import main.java.Ui.Ui;

public class WrongCommand extends Command {

    private String command;

    public WrongCommand(String command) {
        super();
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AnonymousException {
        throw new AnonymousException(this.command);
    }
}
