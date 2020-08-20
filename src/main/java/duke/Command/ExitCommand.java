package duke.Command;

import duke.*;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }
}
