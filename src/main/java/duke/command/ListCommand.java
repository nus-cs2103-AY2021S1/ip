package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represent a "List" Command
 * A <code>ListCommand</code> object that corresponds to a command of an input "list"
 */
public class ListCommand extends Command {

    /**
     * Prints the current list of task in the taskList
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int i = 1;
        if (taskList.getList().isEmpty()) {
            System.out.println("List is Empty");
        }
        for(Task t : taskList.getList()) {
            System.out.println(i + "." + t);
            i++;
        };
    }
}
