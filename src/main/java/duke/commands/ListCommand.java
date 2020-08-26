package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        this.commandName = "FullList";
        this.isExit = false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showList();
        list.printList("All");
    }
}
