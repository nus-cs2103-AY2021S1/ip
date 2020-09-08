package duke.command;

import duke.error.DeadlineDateParseException;
import duke.error.EventDateParseException;
import duke.error.IncorrectFormat;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import java.io.IOException;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IncorrectFormat, IOException, EventDateParseException, DeadlineDateParseException;

    public abstract boolean isExit();
}
