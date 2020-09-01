package duke.commands;

import duke.util.OutputUi;
import duke.util.Storage;
import duke.tasks.Task;
import duke.util.TaskList;
import duke.util.Ui;
import duke.DukeException;

import java.util.ArrayList;

/**
 * Command invoked when list is passed as input. Prints out current contents of TaskList.
 */
public class ListCommand extends Command {
    /**
     * Prints the contents of the TaskList
     * @param tasks TaskList containing Tasks.
     * @param ui Ui object that handles printing of any necessary output.
     * @param storage Storage object that handles saving Tasks to hard disk.
     * @throws DukeException DukeException.
     * @return
     */
    public String execute(TaskList tasks, OutputUi ui, Storage storage) {
        ArrayList<Task> tasklist = tasks.getTasklist();

        ui.reset();
        ui.addSentence("here is ur list");
        int count = 1;
        for (Task t : tasklist) {
            ui.addSentence("\t" + count + ". " + t);
            count++;
        }

        return ui.getResponse();
    }
}
