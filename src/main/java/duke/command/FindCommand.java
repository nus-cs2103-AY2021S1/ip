package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/** Represents a command to find and prints tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String[] nextCommandArr;

    public FindCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    /**
     * Lists all tasks containing the keyword in the taskList.
     * @param tasks is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @return A string listing all the relevant tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = nextCommandArr[1];
        return ui.listRelevantTasks(tasks, keyword);
    }

    @Override
    public boolean continueRunning() {
        return true;
    }
}
