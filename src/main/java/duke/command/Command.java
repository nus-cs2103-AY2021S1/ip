package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.task.TaskList;
import duke.component.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }

}
