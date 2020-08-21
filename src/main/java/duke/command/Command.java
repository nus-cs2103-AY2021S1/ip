package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

abstract public class Command {

    protected boolean isExit = false;

    public boolean isExit() {
        return isExit;
    }
    abstract public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
