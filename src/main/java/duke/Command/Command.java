package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public final String input;

    public Command(String input) {
        this.input = input;
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    abstract public boolean isExit();
}