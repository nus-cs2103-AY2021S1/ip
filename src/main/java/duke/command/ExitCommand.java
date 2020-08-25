package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
