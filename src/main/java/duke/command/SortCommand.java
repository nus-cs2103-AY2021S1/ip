package duke.command;

import java.io.IOException;

import duke.UserInterface;
import duke.exception.DukeIndexException;
import duke.exception.DukeListException;
import duke.task.TaskList;

/**
 * SortCommand class to sort tasking accordingly to deadline.
 * @author Kor Ming Soon
 */
public class SortCommand extends Command {


    /**
     * Execution of sorting of tasking based on deadline upon user prompting.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @return returns a list of the sorted tasks.
     * @throws DukeListException
     * @throws DukeIndexException
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui) throws DukeListException, IOException {
        return new ListCommand().execute(taskList.sortTask(), ui);
    }
}
