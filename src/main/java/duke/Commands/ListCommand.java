package duke.Commands;

import duke.Storage.Storage;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList(ui);
    }
}
