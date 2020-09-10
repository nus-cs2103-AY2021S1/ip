package duke.commands;

import static duke.util.FormatChecker.checkFindFormat;
import static duke.util.Keyword.KEYWORD_FIND_SUCCESS;

import duke.exception.InvalidFormatFindException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

/**
 * Class that simulates the find command of the user.
 */
public class FindCommand extends Command {
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidFormatFindException {
        checkFindFormat(inputArr);
        return findTasks(tasks, inputArr[1], ui);
    }

    /**
     * Find and displays the list of task based on the user's input.
     *
     * @param tasks Object that contains the list of tasks.
     * @param keyword The task that the user is looking for.
     * @param ui Object that deals with interactions with the user.
     * @return A String message stating all the tasks that contains the particular keyword that
     * the user has keyed in.
     */
    private String findTasks(TaskList tasks, String keyword, Ui ui) {
        StringBuilder finalMessage = new StringBuilder();
        finalMessage = buildString(finalMessage, tasks, keyword);
        return getResult(finalMessage, ui, keyword);
    }

    /**
     * Construct the list of task based on the use's input.
     *
     * @param finalMessage Empty message.
     * @param tasks Object that contains the list of tasks.
     * @param keyword The task that the user is looking for.
     * @return list of task based on the use's input.
     */
    private StringBuilder buildString(StringBuilder finalMessage, TaskList tasks, String keyword) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String description = task.getDescription().toLowerCase();
            if (description.contains(keyword.toLowerCase())) {
                finalMessage.append(task.toString()).append("\n");
            }
        }
        return finalMessage;
    }
    private boolean checkEmptyMessage(StringBuilder finalMessage) {
        return finalMessage.toString().equals("");
    }

    /**
     * Return the list of tasks that matches the user's input.
     *
     * @param finalMessage result of finding any list of tasks that match the user's input.
     * @param ui Object that deals with interactions with the user.
     * @param keyword The task that the user is looking for.
     * @return
     */
    private String getResult(StringBuilder finalMessage, Ui ui, String keyword) {
        if (checkEmptyMessage(finalMessage)) {
            String noTask = String.format("No available task matches %s\n", keyword);
            return ui.messageFormatter(noTask);
        } else {
            assert finalMessage.toString().length() > 0 : "Message is empty";
            return ui.messageFormatter(KEYWORD_FIND_SUCCESS, finalMessage.toString());
        }
    }
}
