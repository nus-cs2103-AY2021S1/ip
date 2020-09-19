package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        TaskList keywordTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(keyword)) {
                keywordTasks.add(task);
            }
        }
        ui.FoundItems(keywordTasks, this.keyword);
    }
}