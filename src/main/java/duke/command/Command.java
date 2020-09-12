package duke.command;

import duke.DukeException;
import duke.Statistics;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command which is an abstract class.
 */
public abstract class Command {

    /**
     * Carries out different actions corresponding to type of command.
     *
     * @param tasks Task list representing current tasks.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     * @throws DukeException If unable to either carry out the specific actions.
     */
    public abstract String execute(TaskList tasks, Storage storage, Statistics stats) throws DukeException;

}
