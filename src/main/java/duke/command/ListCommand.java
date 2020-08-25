package duke.command;

import duke.*;
import duke.exception.*;

/**
 * ListCommand class to execute list command when given by user.
 * @author Kor Ming Soon
 */
public class ListCommand extends Command {

    /**
     * Execute command to begin execution of 'List'
     * @param tasklist list of tasks to be referenced from.
     * @param ui UserInterface for the command to prompt.
     * @throws DukeListException When the list is empty.
     */
    @Override
    public void execute(Tasklist tasklist, UserInterface ui) throws DukeListException {
        if (tasklist.getTaskSize() != 0) {
            ui.listTask();
            for (int i = 0; i < tasklist.getTaskSize(); i++) {
                ui.printTask(i + 1, tasklist.get(i).toString());
            }
        } else {
            throw new DukeListException("Your list is empty.");
        }
    }
}
