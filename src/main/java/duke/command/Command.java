package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.io.IOException;

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
    boolean isExit();
}
