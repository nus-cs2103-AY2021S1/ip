package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    
    public ByeCommand(String[] inputArr) {
        super(inputArr);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodBye();
        storage.record(tasks);
        setExitStatus(true);
    }
}
