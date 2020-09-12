package duke.command;

import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        MerchandiseList merchandises) {
        if (tasks.getTasks().size() == 0) {
            ui.setResponse("There are no tasks in your list");
        } else {
            String response = "Here are the tasks in your list:\n";
            for (int i = 1; i < tasks.getTasks().size() + 1; i++) {
                response += i + ". " + tasks.getTasks().get(i - 1) + "\n";
            }
            ui.setResponse(response);
        }
    }
}
