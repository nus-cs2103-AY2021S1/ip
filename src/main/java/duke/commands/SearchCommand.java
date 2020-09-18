package duke.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Represents a seach command when the user wants to
 * search for tasks with a specific keyword.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    private String[] commandKeywords;

    /**
     * Creates an instance of a Search Command with the appropriate
     * search keyword.
     *
     * @param commandKeywords Search Keywords.
     */
    public SearchCommand(String... commandKeywords) {
        super("Search Command", false);
        this.commandKeywords = commandKeywords;
    }

    /**
     * Executes the Command.
     * Searches the list of tasks and prints the tasks that
     * match the keyword.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui       User interface object.
     * @param storage  Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Set<Task> tasksFound = new HashSet<>();
        try {
            for (String keyword : commandKeywords) {
                findTasksWithKeyword(keyword, tasksFound, taskList);
            }
            ArrayList<Task> tasks = new ArrayList<>(tasksFound);
            return ui.displayAllItems(tasks);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("There's no such element!");
        }
    }


    private void findTasksWithKeyword(String keyword, Set<Task> taskSet, TaskList taskList)
            throws IndexOutOfBoundsException {
        String keywordPattern = "(.*)" + keyword + "(.*)";
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTaskAtIndex(i);
            if (task.toString().matches(keywordPattern)) {
                taskSet.add(task);
            }
        }
    }
}
