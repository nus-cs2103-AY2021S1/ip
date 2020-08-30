package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand class contains methods pertaining to the FindCommand.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.print(taskList.find(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
