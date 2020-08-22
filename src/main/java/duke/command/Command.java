package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Storage;
import duke.Ui;

public interface Command {
    void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException;

    default boolean isExit() {
        return false;
    }
}
