package duke.command;

import duke.resource.TaskList;
import duke.resource.Wrapper;
import duke.ui.Ui;

import java.util.List;

public class ListCommand implements Command {

    @Override
    public String apply(Wrapper wrapper, List<String> input) {
        TaskList tasks = wrapper.getTaskList();
        Ui ui = wrapper.getUi();
        return ui.showList(tasks);
    }

}
