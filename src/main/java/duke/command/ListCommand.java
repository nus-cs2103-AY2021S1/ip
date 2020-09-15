package duke.command;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.response.NormalResponse;
import duke.response.Response;
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
     * @return Response information of all the tasks in taskList/
     */
    public static Response execute(TaskList taskList) {
        int len = taskList.size();
        ArrayList<String> msgBody = new ArrayList<>();
        boolean isEmpty = len == 0;
        msgBody.add(isEmpty ? RESPONSE_EMPTY_LIST : RESPONSE_NON_EMPTY_LIST);

        Stream
                .iterate(1 , i -> i <= len, i -> i + 1)
                .forEach(i -> {
                    Task task = taskList.get(i - 1);
                    String line = i + "." + task.toString();
                    msgBody.add(line);
                });

        String response = String.join("\n", msgBody);
        return new NormalResponse(response);
    }
}
