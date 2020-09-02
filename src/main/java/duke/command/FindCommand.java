package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Implements find command objects.
 *
 * @author Audrey Felicio Anwar
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            ui.accumulateResponses(" Sorry no tasks with matching keyword was found :(");
        } else {
            ui.accumulateResponses(" Let me list out the matching tasks for you...");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.accumulateResponses(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        return ui.getResponses();
    }
}
