package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class ByeCommand extends Command {
    
    public ByeCommand() {
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {
        ui.close();
    }
}
