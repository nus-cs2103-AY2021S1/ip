package olivia.command;

import olivia.logic.Storage;
import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.task.Task;
import olivia.ui.Ui;

import java.util.List;

public class FindCommand implements Command {

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        Storage storage = wrapper.getStorage();
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
