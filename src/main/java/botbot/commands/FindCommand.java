package botbot.commands;

import botbot.utils.Storage;
import botbot.utils.TaskList;
import botbot.ui.Ui;
import botbot.tasks.Task;

/**
 * Searches for tasks that match the specified keyphrase.
 */
public class FindCommand extends Command {
    public static final String COMMAND_KEYWORD = "find";
    private final String keyphrase;
    
    /**
     * Creates a find command to search for the specified keyphrase in the task list.
     *
     * @param keyphrase Keyphrase to search.
     */
    public FindCommand(String keyphrase) {
        this.keyphrase = keyphrase.toLowerCase();
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
            String lowerCaseTaskDesc = task.getDescription().toLowerCase();
            if (lowerCaseTaskDesc.contains(keyphrase)) {
                matches.add(task);
            }
        }
        return ui.showFindResponse(matches);
    }
}
