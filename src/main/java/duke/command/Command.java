package duke.command;

import duke.error.IncorrectFormat;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IncorrectFormat, IOException;

    public abstract boolean isExit();
}
