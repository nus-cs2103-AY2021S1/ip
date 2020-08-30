package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showFind(key);
        list.printList(key);
    }
}
