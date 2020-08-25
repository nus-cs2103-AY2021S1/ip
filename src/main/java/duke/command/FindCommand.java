package duke.command;

import duke.error.DukeError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> list = taskList.getTaskList();
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                foundList.add(task);
            }
        }
        ui.displayMatchingList(foundList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
