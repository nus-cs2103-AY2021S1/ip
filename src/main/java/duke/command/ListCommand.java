package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
    }
    
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {
        ui.printToConsole(taskList.convertTaskListToString());
    }
    
}
