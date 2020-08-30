package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;

/**
 * used to handle case when list is the keyword
 */
public class ListCommand extends Command {
    /**
     * assigns string to a value of string
     * @param string assigns string to this this.string
     */
    public ListCommand(String string) {
        super(string);
    }

    /**
     * lists all the tasks that are currently present in the tasks.
     * @param tasks to access the list and print them
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String s = "";
        for(int i = 0; i < tasks.getAllTasks().size(); i++){
            System.out.println("  " + tasks.getAllTasks().get(i));
            s = s + "\n" + "  " + tasks.getAllTasks().get(i);
        }
        return s;
    }

}

