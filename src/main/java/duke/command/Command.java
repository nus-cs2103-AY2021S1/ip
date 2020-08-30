package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasklist, Ui ui) throws DukeException;
}
