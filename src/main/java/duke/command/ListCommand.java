package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
