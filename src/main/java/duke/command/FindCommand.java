package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a searching {@link duke.task.Task} command.
 *
 * @author Tee Kok Siang
 */
public class FindCommand extends Command {
    /** Minimum number of word count for a FindCommand */
    public static final int MIN_WORD_COUNT = 6;
    /** Position for extracting query for a FindCommand */
    public static final int QUERY_START_POSITION = 5;

    private final String query;

    /**
     * Constructs a DoneCommand object.
     *
     * @param query Query keyword for searching tasks.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Executes a FindCommand to search tasks.
     * Search the task list by the query keyword.
     * Displays the search result.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     * @return Formatted response message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(query)) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.size() == 0) {
            ui.printResponse("No task found...");
            return "No task found...";
        }
        StringBuilder taskMessage = new StringBuilder();
        taskMessage.append("Here are the matching tasks in your list:");
        taskMessage.append(TaskList.getTaskMessage(matchedTasks));
        ui.printResponse(taskMessage.toString());
        return taskMessage.toString();
    }
}
