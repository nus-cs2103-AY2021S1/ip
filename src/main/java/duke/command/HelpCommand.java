package duke.command;

import duke.TaskList;
import duke.UserInterface;
import duke.exception.DukeIndexException;
import duke.exception.DukeListException;

/**
 * HelpCommand for when user requires help.
 *
 * @author Kor Ming Soon
 */
public class HelpCommand extends Command {

    /**
     * Execute command for Help.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @return the help message to guide users.
     * @throws DukeListException
     * @throws DukeIndexException
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui) throws DukeListException, DukeIndexException {
        return ui.sendWelcomeMessage();
    }
}
