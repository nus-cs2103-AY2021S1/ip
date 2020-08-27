package duke.command;

import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;
import duke.task.DukeTask;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {

        ArrayList<DukeTask> filteredList = new ArrayList<>();
        for (DukeTask task : taskList.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                filteredList.add(task);
            }
        }

        if (filteredList.size() == 0) {
            uiManager.printFindCannotBeFound(keyword);
        } else {
            uiManager.printFindFilteredList(keyword, filteredList.size() > 1);
            for (int i = 0; i < filteredList.size(); i++) {
                uiManager.printNumberedTask(filteredList.get(i), i);
            }
        }
    }
}

