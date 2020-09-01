package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.print(tasks.toString());
    }

    @Override
    public String execute(TaskList tasks, Storage store) throws DukeException {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
