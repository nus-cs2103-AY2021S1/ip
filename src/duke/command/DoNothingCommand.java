package duke.command;

import duke.exception.DukeException;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

public class DoNothingCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
