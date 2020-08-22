package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException;
}