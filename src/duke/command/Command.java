package duke.command;

import duke.exception.DukeException;
import main.java.Storage;
import main.java.TaskList;
import main.java.Ui;

abstract public class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
