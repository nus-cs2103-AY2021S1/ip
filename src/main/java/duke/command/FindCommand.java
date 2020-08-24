package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filtered = taskList.find(keyword);
        ui.print("Here are the matching tasks in your list:");
        for (int i = 0; i < filtered.size(); i++) {
            ui.print(String.format("%d. %s", i + 1, filtered.show(i)));
        }
    }
}
