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

    /**
     * Executes the BYE command, which dispalys an exiting message.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("OKAIS I IZ GOIN 2 NOM BYEEEEE C U !!!1!1!!");
    }

    @Override
    public String toString() {
        return this.cmd.toString();
    }
}
