package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.io.Storage;
import duke.io.TaskList;

public abstract class Command {

  public abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeException;

  public abstract boolean isExit();
}
