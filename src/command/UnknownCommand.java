package command;

import exception.DukeException;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;
import exception.UnknownCommandException;

public class UnknownCommand extends Command {
    public UnknownCommand(String[] splitCommand) {
        super(splitCommand);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new UnknownCommandException();
    }

    public boolean isExit() {
        return false;
    }
}
