package duke.command;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskList;

// Handles all the logic behind any "find" command from the user.
public class FindCommand extends Command {
    private static final String RESPONSE_NO_MATCHES = "None of the tasks matches the keyword!";
    private static final String RESPONSE_MATCHES_FOUND = "Here are the matching tasks in your list:\n";

    /**
     * Executes any "due" command issued by the user.
     * Returns the information of the tasks containing the keyword specified by the user.
     *
     * @param in String "due" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user including tasks containing the keyword.
     */
    public static Response execute(String in, TaskList taskList) {
        String keyword = in.replaceFirst("find ", "").trim();

        ArrayList<String> msg = new ArrayList<>();
        int len = taskList.size();

        Stream
                .iterate(1 , i -> i <= len, i -> i + 1)
                .forEach(i -> {
                    Task task = taskList.get(i - 1);
                    if (task.containsKeyword(keyword)) {
                        String output = i + "." + task.toString();
                        msg.add(output);
                    }
                });

        boolean hasMatches = msg.size() > 0;
        String firstLine = hasMatches
                ? RESPONSE_MATCHES_FOUND
                : RESPONSE_NO_MATCHES;

        String response = firstLine + String.join("\n", msg);
        return new NormalResponse(response);
    }
}
