package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {
    protected CMD cmd;
    protected boolean isExit;

    public Command() {
        this.cmd = CMD.DEFAULT;
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the default command, which displays a standard string regardless
     * of input.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("CAN I HAZ CHEEZBURGER?");
    }

    @Override
    public String toString() {
        return this.cmd.toString();
    }
}