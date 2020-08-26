package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class ListCommand {
    public static String execute(TaskList taskList) {
        int len = taskList.size();
        ArrayList<String> msgBody = new ArrayList<>();
        msgBody.add((len == 0) ? "There are no tasks in your list!" : "Here are the tasks in your list:");

        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            String line = i + "." + task.toString();
            msgBody.add(line);
        }

        return String.join("\n", msgBody);
    }
}
