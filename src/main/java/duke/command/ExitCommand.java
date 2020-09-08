package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.saveTaskList(tasks);
        storage.finalise();
        return ui.exit();
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof ExitCommand;
    }
}
