package duke.commands;

import java.util.stream.IntStream;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;



/**
 * Class that simulates the add command of the user.
 */

public class ListCommand extends Command {
    private static final String EMPTY_MSG = "Your list is empty!!!";
    private static final String SHOW_TASK = "Here are the tasks in your list:";
    /**
     * Creates an ListCommand object.
     *
     * @param inputArr Array of length 2 that contains information of the user input
     *                 At index 0, contains the type of command
     *                 At index 1, contains the message of the command.
     */
    public ListCommand(String[] inputArr) {
        super(inputArr);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        return showListTasks(tasks, ui);
    }

    /**
     * Printing out all the task in the list.
     *
     * @param tasks Object contains the task list.
     * @param ui Object that deals with interactions with the user.
     * @return A string representing the information of the list of tasks.
     */
    private String showListTasks(TaskList tasks, Ui ui) {
        if (tasks.size() == 0) {
            return ui.messageFormatter(EMPTY_MSG);
        } else {
            StringBuffer finalMessage = new StringBuffer(SHOW_TASK).append("\n");
            IntStream.range(1, tasks.size() + 1).forEach(num -> finalMessage.append(tasks.get(num - 1)).append("\n"));
            return ui.messageFormatter(finalMessage.toString());
        }
    }
}
