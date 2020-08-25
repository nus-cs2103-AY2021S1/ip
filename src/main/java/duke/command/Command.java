package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * Represents a command.
 * @author Lingy12.
 */
public interface Command {

    /**
     * Executes a certain command.
     * @param tasks List of the tasks.
     * @param ui UI manager for Duke.
     * @param storage Manager of the file I/O.
     * @throws DukeException when there is exception in execution process.
     */
    void excute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    boolean isExit();
}
