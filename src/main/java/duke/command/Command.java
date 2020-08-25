package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class Command {

    public boolean isExit = false;

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {}
}
