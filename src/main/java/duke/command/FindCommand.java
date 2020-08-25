package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String keyword = ui.getKeyword();
        taskList.find(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
