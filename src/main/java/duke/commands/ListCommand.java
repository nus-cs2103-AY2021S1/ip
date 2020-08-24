package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        ui.listItems(taskList);
    }
}
