package duke.command;

import duke.error.DukeError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

import java.util.Comparator;

public class SortCommand implements Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        taskList.sort(Comparator.comparing(Task::getActualDate));
        return UI.SORT_MESSAGE;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
