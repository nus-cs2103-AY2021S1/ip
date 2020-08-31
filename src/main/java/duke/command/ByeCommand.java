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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("OKAIS I IZ GOIN 2 NOM BYEEEEE C U !!!1!1!!");
    }
}
