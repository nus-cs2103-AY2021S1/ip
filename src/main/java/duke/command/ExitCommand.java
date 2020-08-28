package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

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
