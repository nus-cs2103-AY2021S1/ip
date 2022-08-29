package chattybot.commands;

import chattybot.Storage;
import chattybot.TaskList;
import chattybot.Ui;
import chattybot.tasks.Task;

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
     * @return UI message after executing find command.
     */
    public String execute(Ui ui, Storage listStorage, TaskList taskList) {
        TaskList keywordTasks = new TaskList();
        assert taskList.getNumTask() >= 0 : "Something went wrong in your task list!";
        for (int i = 0; i < taskList.getNumTask(); i++) {
            Task nextTask = taskList.get(i);
            if (nextTask.toString().contains(keyword)) {
                keywordTasks.add(nextTask);
            }
        }
        return ui.printFoundItems(keywordTasks, this.keyword);
    }
}
