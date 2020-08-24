package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public abstract class Command {
    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

    }

    public boolean isExit() {
        return isExit;
    }
}
