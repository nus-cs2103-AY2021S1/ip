package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.tool.TaskList;

/**
 * Represents a command.
 * @author Lingy12.
 */
public interface Command {

    /**
     * Executes a certain command.
     *
     * @param tasks List of the tasks.
     * @param storage Manager of the file I/O.
     * @throws DukeException when there is exception in execution process.
     */
    void execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Checks whether the command is an exit command.
     *
     * @return True if the command is an instance of ExitCommand, false otherwise.
     */
    boolean isExit();

    String getResponse();
}
