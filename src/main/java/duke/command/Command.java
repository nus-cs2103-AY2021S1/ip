package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;
import java.util.List;

public interface Command {
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
