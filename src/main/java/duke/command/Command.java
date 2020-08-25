package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    protected final String[] parsedCommand;

    public Command(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

}
