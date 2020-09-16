package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.displayMatchingList(taskList.findList(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
