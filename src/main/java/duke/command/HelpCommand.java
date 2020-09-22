package duke.command;

import duke.logic.Ui;
import duke.task.TaskList;

public class HelpCommand implements Command {
    public HelpCommand() {
    }

    @Override
    public String execute(TaskList tasks) {
        return Ui.printHelpMessage();
    }

    @Override
    public boolean setIsFinished() {
        return false;
    }
}
