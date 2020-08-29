package duke.command;

import java.util.ArrayList;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;
import duke.task.Task;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> list = taskList.getTaskList();
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                foundList.add(task);
            }
        }
        return ui.displayMatchingList(foundList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
