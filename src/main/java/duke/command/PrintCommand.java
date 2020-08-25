package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

import java.io.IOException;

public class PrintCommand extends Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printList(storage);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
