package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder("\t Here are the tasks in your list:\n");
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            str.append("\t ").append(i + 1).append(".")
                    .append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                str.append("\n");
            }
        }
        ui.show(str.toString());
    }
}
