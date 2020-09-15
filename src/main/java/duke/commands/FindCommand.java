package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the command that finds the tasks that contain the keyword. */
public class FindCommand extends Command {

    /** The keyword to query. */
    private String keyword;

    /** Constructs a new FindCommand object with the specified keyword.
     *
     * @param keyword The keyword to query.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /** Prints out the list of tasks that contain the keyword in Duke format.
     *
     * @param taskList The list of tasks.
     * @param ui The UI that prints out messages in Duke format.
     * @param storage The storage system that saves the taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        setDialog(ui.formatFindTasks(matchingTasks));
    }
}
