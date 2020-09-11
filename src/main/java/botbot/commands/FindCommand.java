package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;
import botbot.tasks.Task;

/**
 * Searches for tasks that match the specified keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_KEYWORD = "find";
    private final String keyword;
    
    /**
     * Creates a find command to search for the specified keyword in the task list.
     *
     * @param keyword Keyword to search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param storage Storage to save updated task list to.
     * @param tasks Task list to add task to.
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        TaskList matches = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        return ui.showFindResponse(matches);
    }
}
