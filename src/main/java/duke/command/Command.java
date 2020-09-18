package duke.command;

import duke.task.TaskList;
import duke.dukeexception.DukeException;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    public Command() {}

    public abstract boolean isExit();
    public abstract String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException;
}
