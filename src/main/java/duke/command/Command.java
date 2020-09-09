package duke.command;

import java.io.IOException;

import duke.error.*;
import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;


public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws IncorrectFormat, IOException, EventDateParseException, DeadlineDateParseException, DeleteListEmptyException, DeleteOutOfBounds, DeleteNegativeIndex;

    public abstract boolean isExit();
}
