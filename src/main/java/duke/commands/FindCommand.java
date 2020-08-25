package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : taskList.tasks) {
            if (task.name.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.formatFindTasks(matchingTasks);
    }
}
