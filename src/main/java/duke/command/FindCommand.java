package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.FindException;
import duke.task.Task;

/**
 * Represent a "Find" Command
 * A <code>FindCommand</code> object that corresponds to a command of an input "find"
 * and contains a description as a String
 */
public class FindCommand extends Command {

    String description;

    /**
     * Constructor of the FindCommand Class
     *
     * @param description description of the task
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Goes through the list of task to search for matching description and
     * assign it to a new Arraylist to be printed out
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     * @throws DukeException exception to be thrown if there is no matching results
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String list = "";
        int index = 1;
        for (Task task : taskList.getList()) {
            if (task.toString().contains(description)) {
                list += "\n" + index + ". " + task.toString();
                index++;
            }
        }
        if (list.equals("")) {
            throw new FindException("There is no matching results. Please re-enter:");
        } else {
           ui.print("Here are the matching tasks in your list:" + list);
        }

    }
}
