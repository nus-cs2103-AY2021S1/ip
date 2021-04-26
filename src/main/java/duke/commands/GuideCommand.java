package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.TaskList;


/**
 * Display the chatbot guide.
 */
public class GuideCommand implements Command {

    /**
     * Displays the chatbot guide.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The reminder message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayGuide();
    }
}
