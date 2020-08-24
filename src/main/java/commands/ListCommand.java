package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class ListCommand extends Command {

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
