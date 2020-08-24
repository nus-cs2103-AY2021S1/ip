package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
