package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.print(String.format("%d. %s", i + 1, taskList.show(i)));
        }
    }
}
