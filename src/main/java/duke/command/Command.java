package duke.command;

import duke.*;
import duke.exception.*;

/**
 * Abstract command class
 * @author Kor Ming Soon
 */
abstract public class Command {

    /**
     * Commmand that all commands are able to execute, with varying purposes dependent on the command.
     * @param tasklist list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeListException For when list is empty.
     * @throws DukeIndexException Index given does not match the list.
     */
    public abstract void execute(Tasklist tasklist, UserInterface ui) throws DukeListException, DukeIndexException;

}
