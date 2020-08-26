package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList keywordTasks = new TaskList();
        tasks.getTaskList().forEach((task) -> {
            if(task.getDescription().contains(keyword)) {
                keywordTasks.addTask(task);
            }
        });
        ui.showFind(keywordTasks);
    }
}
