package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a command to find tasks related to a keyword in the task list.
 *
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates FindCommand with given keyword to be used in searching.
     *
     * @param keyword Keyword to find relevant tasks in the task list.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the main logic to find tasks relevant to the keyword in the task list.
     *
     * @param ui
     * @param listStorage
     * @param taskList
     */
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        TaskList keywordTasks = new TaskList();
        for (int i = 0; i < taskList.getNumTask(); i++) {
            Task nextTask = taskList.get(i);
            if (nextTask.toString().contains(keyword)) {
                keywordTasks.add(nextTask);
            }
        }
        ui.printFoundItems(keywordTasks, this.keyword);
    }
}
