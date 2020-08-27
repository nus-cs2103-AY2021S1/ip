package duke.command;

import duke.component.Storage;
import duke.task.TaskList;
import duke.component.Ui;

public class ByeCommand extends Command {

    /**
     * Used to show bye message to user
     *
     * @param tasks list of tasks.
     * @param ui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * to break the loop and terminate the program.
     *
     * @return true that satisfies the condition in main class and breaks the loop.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
