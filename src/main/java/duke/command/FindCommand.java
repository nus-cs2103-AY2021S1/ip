package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

// Handles all the logic behind any "find" command from the user
public class FindCommand extends Command {
    /**
     * Executes any "due" command issued by the user.
     * Returns the information of the tasks containing the keyword specified by the user.
     *
     * @param in String "due" command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @return String response message to user including tasks containing the keyword
     */
    public static String execute(String in, TaskList taskList) {
        String keyword = in.replaceFirst("find ", "").trim();

        ArrayList<String> msg = new ArrayList<>();
        int len = taskList.size();
        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            if (task.containsKeyword(keyword)) {
                String output = i + "." + task.toString();
                msg.add(output);
            }
        }

        String firstLine = msg.size() == 0
                ? "None of the tasks matches the keyword!"
                : "Here are the matching tasks in your list:\n";

        return firstLine + String.join("\n", msg);
    }
}
