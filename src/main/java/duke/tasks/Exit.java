package duke.tasks;

import duke.tool.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

/**
 * The Exit command when user want to quit the Duke.
 */
public class Exit extends Task {

    /**
     * Constructs a exit Task.
     */
    public Exit() {
        super("exit", true);
        this.isExit = true;
    }

    /**
     * Excute the task.
     * @param tasklist
     * @param ui
     * @param storage
     * @return
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.showGoodbye();
    };

}
