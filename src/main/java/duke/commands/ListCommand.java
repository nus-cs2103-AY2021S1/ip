package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String response = "\t Here are the tasks in your list:\n";
        ui.show(tasksToString(taskList.getTasks(), response));
    }

    public static String tasksToString(ArrayList<Task> tasks, String initialString) {
        StringBuilder str = new StringBuilder(initialString);
        for (int i = 0; i < tasks.size(); i++) {
            str.append("\t ").append(i + 1).append(".")
                    .append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
