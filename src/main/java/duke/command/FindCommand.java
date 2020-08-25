package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand implements Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(keyword);
        if(tasks.isEmpty()) {
            ui.print("There are no items in the list right now.");
        } else {
            ui.print(tasks.filter(keyword).itemize("Here are the matching tasks in your list:"));
        }
    }
}
