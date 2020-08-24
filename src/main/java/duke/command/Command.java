package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

abstract public class Command {
    public abstract void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
