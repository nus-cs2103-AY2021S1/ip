package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * An interface that models a basic Command.
 */
public interface Command {

    /**
     * Contains the logic required to execute the command.
     * @param ui The ui of Duke.
     * @param storage The storage object.
     * @param tasks The taskList.
     * @throws DukeException
     */
    void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException;

    /**
     * Checks whether command indicates an intent
     * to exit Duke.
     * @return Whether the Command signals an intent
     * to exit Duke.
     */
    default boolean isExit() {
        return false;
    }
}
