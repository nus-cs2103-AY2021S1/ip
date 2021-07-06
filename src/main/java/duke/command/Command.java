package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
