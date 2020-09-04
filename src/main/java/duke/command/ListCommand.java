package duke.command;


import duke.TaskList;
import duke.UserInterface;
import duke.exception.DukeListException;

/**
 * ListCommand class to execute list command when given by user.
 *
 * @author Kor Ming Soon
 */
public class ListCommand extends Command {

    /**
     * Execute command to begin execution of 'List'.
     *
     * @param taskList list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @return a list of tasking.
     * @throws DukeListException When the list is empty.
     */
    @Override
    public String execute(TaskList taskList, UserInterface ui) throws DukeListException {
        if (taskList.getTaskSize() != 0) {
            String response = ui.listTask();
            for (int i = 0; i < taskList.getTaskSize(); i++) {
                response += ui.printTask(i + 1, taskList.getTaskDetail(i).toString());
            }
            return response;
        } else {
            throw new DukeListException("Your list is empty.");
        }
    }
}
