package duke.command;

import duke.DukeException;
import duke.datetime.DateTimeUtility;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String filter;

    public FindCommand(String filter) {
        super();
        this.cmd = CMD.FIND;
        this.filter = filter;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        String ret = tasklist.filterTasksByDescription(this.filter);
        if (ret.isEmpty()) {
            return "U HAZ NUTHIN DAT GOT "
                    + this.filter + " INSIDE LULZIES";
        } else {
            return "U HAS DEES TINGS IN UR LIST DAT MATCH "
                    + this.filter + ": \n"
                    + ret;
        }
    }
}
