package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class ByeCommand extends Command {

    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showBye();
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
