package duke.command;

import duke.TaskList;
import duke.UserInterface;
import duke.exception.DukeIndexException;
import duke.exception.DukeListException;

/**
 * ByeCommand for when the Bye command is prompted by User
 * @author Kor Ming Soon
 */
public class ByeCommand extends Command {

    /**
     * Execution command for Bye.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @return exit message for when the users stops interacting with the bot.
     * @throws DukeListException
     * @throws DukeIndexException
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui) throws DukeListException, DukeIndexException {
        return ui.exitMessage();
    }
}
