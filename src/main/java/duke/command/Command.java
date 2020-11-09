package duke.command;

import java.io.IOException;

import duke.UserInterface;
import duke.exception.DukeIndexException;
import duke.exception.DukeListException;
import duke.task.TaskList;

/**
 * Abstract command class.
 *
 * @author Kor Ming Soon
 */
abstract class Command {

    /**
     * Command that all commands are able to execute, with varying purposes dependent on the command.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @return response from Duke.
     * @throws DukeListException For when list is empty.
     * @throws DukeIndexException Index given does not match the list.
     */
    public abstract String execute(TaskList taskList,
                                   UserInterface ui) throws DukeListException, DukeIndexException, IOException;

}
