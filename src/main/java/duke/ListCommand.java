package duke;

import java.util.ArrayList;

/**
 * Command invoked when list is passed as input. Prints out current contents of TaskList
 */
public class ListCommand extends Command {
    /**
     * Prints the contents of the TaskList
     * @param tasks TaskList containing Tasks
     * @param ui Ui object that handles printing of any necessary output
     * @param storage Storage object that handles saving Tasks to hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> tasklist = tasks.getTasklist();

        ui.printDivider();
        ui.printMsg("Tasks");

        for (int i = 1; i < tasklist.size() + 1; i++) {
            ui.printMsg("\t" + i + ". " + tasklist.get(i-1));
        }

        ui.printDivider();
    }
}
