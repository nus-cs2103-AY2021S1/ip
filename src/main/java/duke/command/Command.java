package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public boolean isBye = false;
    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;
}
