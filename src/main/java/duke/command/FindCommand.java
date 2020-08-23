package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ArrayList<String> findTasksRepr = tasks.find(keyword);
        ui.printWithWrapper(findTasksRepr, true, false);
    }
}
