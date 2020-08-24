package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Ui;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException;
    public boolean isExit();
}
