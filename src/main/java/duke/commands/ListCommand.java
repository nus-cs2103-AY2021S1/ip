package duke.commands;

import duke.errors.DukeException;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * used to handle case when list is the keyword
 */
public class ListCommand extends Command {
    /**
     * Assigns string to a value of string
     *
     * @param input assigns string to this this.string
     */
    public ListCommand(String input, int lengthOfKeyword) {
        super(input, lengthOfKeyword);
    }

    /**
     * Lists all the tasks that are currently present in the tasks.
     *
     * @param tasks to access the list and print them
     * @param ui
     * @param storage
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return listMessage(tasks);
    }

    /**
     * gives the message when list is called.
     *
     * @param tasks gives the current list, which is used to return current list
     * @return all the current list
     */
    private String listMessage(TaskList tasks) {
        String s = "";
        for (int i = 0; i < tasks.getAllTasks().size(); i++) {
            s = s + "\n" + "  " + tasks.getAllTasks().get(i);
            // concatenates all the string representation of Tasks TaskList
        }
        return s.substring(1);
    }
}

