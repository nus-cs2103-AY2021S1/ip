package duke.command;

import duke.*;
import duke.datetime.DateTimeUtility;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    private String by;

    public ListCommand() {
        super();
        this.by = "";
        this.cmd = CMD.LIST;
    }

    /**
     * Overloaded class constructor if a deadline is spceified for filtering out
     * tasks after the deadline.
     *
     * @param by
     */

    public ListCommand(String by) {
        this();
        this.by = DateTimeUtility.formatString(by);
    }

    /**
     * Executes the LIST command. If no deadline is specified, all tasks in the taskList
     * are printed. If a date / datetime is specified, only tasks that are timed and whose
     * deadline occurs before the given deadline is listed.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            ui.display("UR LIST HAZ NUTHIN LOLOL");
        } else {
            if (this.by.isEmpty()) {
                ui.display("U HAS DEES TINGS IN UR LIST.\n" + taskList.toString());
            } else {
                try {
                    String ret = taskList.filterTasksByDate(this.by);
                    if (ret.isEmpty()) {
                        ui.display("U HAZ NUTHIN DUE/HAPPENIN BY "
                                    + DateTimeUtility.formatString(this.by) + "!! LULZIES");
                    } else {
                        ui.display("U HAS DEES TINGS IN UR LIST DAT R DUE/HAPPENIN BY "
                                + DateTimeUtility.formatString(this.by) + ": \n"
                                + ret);
                    }

                } catch (DukeException e) {} //exception will never be reached
            }
        }
    }

    @Override
    public String toString() {
        return this.cmd.toString() + (this.by.isEmpty() ? "" : " (" + this.by + ")");
    }
}
