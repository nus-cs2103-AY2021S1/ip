package olivia.command;

import java.util.List;

import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.ui.Ui;

/**
 * ListCommand class that lists the tasks in Olivia's TaskList.
 */

public class ListCommand implements Command {

    /**
     * Lists the tasks in Olivia's TaskList numerically.
     * @param wrapper contains Olivia's Storage, TaskList and Ui objects.
     * @param input list that contains the input arguments for the command;
     *              should be empty in this case.
     * @return output String to the user.
     */

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        return ui.showList(tasks);
    }

}
