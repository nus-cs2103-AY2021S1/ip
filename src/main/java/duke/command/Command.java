package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public interface Command {

    void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException;

    default boolean isExit() {
        return false;
    }
}
