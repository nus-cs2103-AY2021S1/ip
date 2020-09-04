package duke.command;

import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;

/**
 * Bye command type.
 * Give indication to end the application.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class ByeCommand extends Command {
    /**
     * Execute bye command logic.
     * Print end message.
     *
     * @param task    not used in this command
     * @param ui      ui class for print.
     * @param storage storage for read, write to file. Not used in this command
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        String result = String.format("%s\n %s\n %s", ui.showLine(), "Bye. Hope to see you again soon!",
                ui.showLine());
        Ui.printString(result);
    }

    /**
     * Change isExit to true to indicate application to end.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
