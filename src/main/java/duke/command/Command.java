package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    public abstract String executeWithOutput(TaskList tasks, Ui ui) throws DukeException;

    public abstract void undo(TaskList tasks);
}
