package duke.command;

import duke.TaskList;
import duke.Ui;

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
