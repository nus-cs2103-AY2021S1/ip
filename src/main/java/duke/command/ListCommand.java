package duke.command;

import duke.Storage;
import duke.task.Tasks;
import duke.Ui;

public class ListCommand extends Command {
    private final CommandType commandType;
    
    public ListCommand() {
        this.commandType = CommandType.LIST;
    }
    
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
