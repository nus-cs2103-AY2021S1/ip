package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

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
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.size() == 0) {
            ui.printMessage(" Sorry no tasks with matching keyword was found :(");
        } else {
            ui.printMessage(" Let me list out the matching tasks for you...");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printMessage(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }
}
