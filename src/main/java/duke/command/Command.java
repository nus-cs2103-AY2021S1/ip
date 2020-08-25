package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {

    protected boolean completed = false;

    public abstract void execute(TaskList list, Storage storage);

    public abstract void printFeedback(Ui ui) throws DukeException;

    public abstract boolean isExit();
}
