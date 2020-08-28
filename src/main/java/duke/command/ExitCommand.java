package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayGoodbye();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
