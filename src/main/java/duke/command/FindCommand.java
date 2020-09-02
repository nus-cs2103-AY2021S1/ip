package duke.command;

import duke.task.TaskList;
import duke.utils.Ui;

public class FindCommand extends Command{

    private String searchText;
    public FindCommand(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showListMessage(tasks.find(searchText));
    }
}
