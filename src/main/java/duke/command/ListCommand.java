package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getList().size() == 0) {
            ui.setResponse("There are no tasks in your list");
        } else {
            String response = "Here are the tasks in your list:\n";
            for (int i = 1; i < tasks.getList().size() + 1; i++) {
                response += i + ". " + tasks.getList().get(i - 1) + "\n";
            }
            ui.setResponse(response);
        }
    }
}
