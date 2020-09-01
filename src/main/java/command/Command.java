package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * Abstract Command class to handle users' command.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-09-01
 */
public abstract class Command {
    public abstract String execute(String inputMsg, TaskList currList, Ui ui) throws DukeException;
}
