package duke.command;

import duke.*;
import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand(String command, boolean isExit) {
        super(command, true);
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui)
            throws IOException {
        storage.saveTasks(tasks);
        ui.printSave();
        ui.printFarewell();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
