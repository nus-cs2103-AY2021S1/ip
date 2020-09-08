package duke.command;

import java.io.IOException;

import duke.error.DeadlineDateParseException;
import duke.error.EventDateParseException;
import duke.error.IncorrectFormat;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

public class SortCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws IncorrectFormat, IOException, EventDateParseException, DeadlineDateParseException {
        tasks.sort(storage);
        return "Tasks sorted";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
