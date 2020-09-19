package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to find a Task in TaskList by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates an FindCommand with keyword given.
     *
     * @param keyword Keyword to be searched against the Tasks.
     */
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
