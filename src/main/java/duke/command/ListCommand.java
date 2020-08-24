package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Tasks;

public class ListCommand extends Command {
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }

    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
