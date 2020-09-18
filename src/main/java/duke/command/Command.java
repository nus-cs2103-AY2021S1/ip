package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.utils.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract String execute(TaskList tasks, Ui ui) throws DukeException;
}
