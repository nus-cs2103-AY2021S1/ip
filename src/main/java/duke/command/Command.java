package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    abstract public void execute(TaskList tasklist, Ui ui) throws DukeException;
}
