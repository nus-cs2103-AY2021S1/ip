package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    protected boolean isExit;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return isExit;
    }

}