package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.stream.IntStream;

/**
 * Class that simulates the find command of the user.
 */
public class FindCommand extends Command {
    private static final String FIND_SUCCESS = "Here are the matching tasks in your list:";

    /**
     * Creates a FindCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public FindCommand(String[] inputArr) {
        super(inputArr);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return findTasks(tasks, inputArr[1], ui);
    }

    /**
     * Find and displays the list of task based on the user's input.
     *
     * @param tasks Object that contains the list of tasks.
     * @param keyword The task that the user is looking for.
     * @return A String message stating all the tasks that contains the particular keyword that
     * the user has keyed in.
     */
    public String findTasks(TaskList tasks, String keyword, Ui ui) {
        StringBuilder finalMessage = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription().toLowerCase();
            if (description.contains(keyword.toLowerCase())) {
                finalMessage.append(task.toString()).append("\n");
            }
        }
        // this means no available search
        if (finalMessage.toString().equals("")) {
            String noTask = String.format("No available task matches %s\n", keyword);
            return ui.messageFormatter(noTask);
        } else {
            return ui.messageFormatter(FIND_SUCCESS, finalMessage.toString());
        }
    }
}
