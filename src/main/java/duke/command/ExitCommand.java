package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public void execute(TaskList list, Storage storage) {
        storage.save(list);
    }

    public void printFeedback(Ui ui) {
        ui.exit();
    }

    public boolean isExit() {
        return true;
    }
}
