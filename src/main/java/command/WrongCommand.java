package main.java.command;

import main.java.exception.AnonymousException;
import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

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
