package fei.command;

import fei.exception.FeiException;
import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public abstract class Command {

    /**
     * Deal with all the changes in Fei's tools.
     *
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @throws FeiException when Command failed to be executed.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws FeiException;

    /**
     * Boolean value to determine the execution of Fei.
     *
     * @return True only when it's a Exit Command.
     */
    public boolean isExit() {
        return false;
    }

}
