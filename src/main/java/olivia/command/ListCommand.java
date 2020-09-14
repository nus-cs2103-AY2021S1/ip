package olivia.command;

import olivia.resource.TaskList;
import olivia.resource.Wrapper;
import olivia.ui.Ui;

import java.util.List;

public class ListCommand implements Command {

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        return ui.showList(tasks);
    }

}
