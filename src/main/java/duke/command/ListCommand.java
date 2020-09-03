package duke.command;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        if (list.size() == 0) {
            ui.setResponse("There are no tasks in your list");
        } else {
            String response = "Here are the tasks in your list:\n";
            for (int i = 1; i < list.size() + 1; i++) {
                response += i + ". " + list.get(i - 1) + "\n";
            }
            ui.setResponse(response);
        }
    }
}
