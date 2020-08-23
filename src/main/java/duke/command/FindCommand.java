package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.search(this.keyword);
        if (filteredTasks.size() == 0) {
            ui.print("No matching tasks found");
        } else {
            ui.print(String.format("Here are the matching tasks in your list:\n%s", filteredTasks));
        }
    }
}
