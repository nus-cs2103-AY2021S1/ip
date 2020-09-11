package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    public abstract String executeWithOutput(TaskList tasks, Ui ui) throws DukeException;
}
