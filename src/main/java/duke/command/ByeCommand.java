package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
        this.cmd = CMD.BYE;
        this.isExit = true;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        return "OKAIS I IZ GOIN 2 NOM BYEEEEE C U !!!1!1!!";
    }

    @Override
    public String toString() {
        return this.cmd.toString();
    }
}
