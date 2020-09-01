package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList t, Ui ui, Storage store) {

    }

    @Override
    public String execute(TaskList tasks, Storage store) throws DukeException {
        return "Bye bye!!!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
