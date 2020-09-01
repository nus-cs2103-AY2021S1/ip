package duke.command;

import duke.Gui;
import duke.component.Storage;
import duke.task.TaskList;


import java.util.ArrayList;

public class ByeCommand extends Command {

    /**
     * Used to show bye message to user
     *
     * @param tasks list of tasks.
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Gui gui, Storage storage, ArrayList<String> responseList) {
        return gui.showBye();
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
