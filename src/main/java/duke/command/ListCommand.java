package duke.command;

import duke.Ui;
import duke.Storage;

import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(String.format("Here are the tasks in your list:\n%s", tasks));
    }
}
