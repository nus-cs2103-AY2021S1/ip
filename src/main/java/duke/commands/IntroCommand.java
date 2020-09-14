package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.TaskList;


/**
 * Display the introduction message.
 */
public class IntroCommand implements Command {

    /**
     * Displays introductions.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The intro message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.intro();
    }
}
