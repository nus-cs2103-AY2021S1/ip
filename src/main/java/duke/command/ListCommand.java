package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

// Handles all the logic behind any "list" command from the user/
public class ListCommand extends Command {
    private static final String RESPONSE_EMPTY_LIST = "There are no tasks in your list!";
    private static final String RESPONSE_NON_EMPTY_LIST = "Here are the tasks in your list:";

    /**
     * Executes any "list" command issued by the user.
     * Iterates the taskList and returns the information of all the tasks in the list.
     *
     * @param taskList TaskList list that contains tasks added by the user/
     * @return String information of all the tasks in taskList/
     */
    public static String execute(TaskList taskList) {
        int len = taskList.size();
        ArrayList<String> msgBody = new ArrayList<>();
        boolean isEmpty = len == 0;
        msgBody.add(isEmpty ? RESPONSE_EMPTY_LIST : RESPONSE_NON_EMPTY_LIST);

        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            String line = i + "." + task.toString();
            msgBody.add(line);
        }

        return String.join("\n", msgBody);
    }
}
