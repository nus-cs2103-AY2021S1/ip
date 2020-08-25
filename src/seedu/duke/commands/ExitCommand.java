package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.Storage;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.closeScanner();
        ui.showByeMessage();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
