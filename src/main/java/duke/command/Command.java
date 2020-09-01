package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;


public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
    boolean isExit();
}
