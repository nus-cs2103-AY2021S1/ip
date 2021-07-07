package duke.command;

import java.util.ArrayList;
import java.util.Arrays;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private ArrayList<String> keywords;

    /**
     * Initializes a FindCommand object.
     *
     * @param keyword The keyword used to find tasks with.
     */
    public FindCommand(String ... keyword) {
        super();
        keywords = new ArrayList<>();
        keywords.addAll(Arrays.asList(keyword));
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList keywordTasks = new TaskList();
        tasks.getTaskList().forEach((task) -> {
            keywords.forEach((keyword) -> {
                if (task.getDescription().contains(keyword)) {
                    keywordTasks.addTask(task);
                }
            });
        });
        return ui.showFind(keywordTasks);
    }
}
