package olivia.command;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Task;
import olivia.ui.Ui;

import java.util.List;

/**
 * FindCommand class that searches for tasks containing the keyword, compiling it
 * and returning it as a list.
 */

public class FindCommand implements Command {

    /**
     * Searches for tasks that contains the input keyword, then returns them in a list.
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     * @param input list that contains the input arguments for the command.
     * @return output String to the user.
     */

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        if (input.size() == 0) {
            return "Please use a keyword you'd like to search with!";
        }
        if (input.size() > 1) {
            return "Please use only one keyword!";
        }
        List<Task> matches = tasks.search(input.get(0));
        return ui.showFind(matches);
    }

}
