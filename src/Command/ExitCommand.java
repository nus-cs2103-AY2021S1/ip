package Command;

import Exception.DukeException;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String[] command) {
        super(command);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
