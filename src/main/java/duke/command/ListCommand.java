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

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        if (tasklist.isEmpty()) {
            return "UR LIST HAZ NUTHIN LOLOL";
        } else {
            if (this.by.isEmpty()) {
                return ("U HAS DEES TINGS IN UR LIST.\n" + tasklist.toString());
            } else {
                String ret = tasklist.filterTasksByDate(this.by);
                if (ret.isEmpty()) {
                    return "U HAZ NUTHIN DUE/HAPPENIN BY "
                                + DateTimeUtility.formatString(this.by) + "!! LULZIES";
                } else {
                    return "U HAS DEES TINGS IN UR LIST DAT R DUE/HAPPENIN BY "
                            + DateTimeUtility.formatString(this.by) + ": \n"
                            + ret;
                }
            }
        }
    }

    @Override
    public String toString() {
        return this.cmd.toString() + (this.by.isEmpty() ? "" : " (" + this.by + ")");
    }
}
