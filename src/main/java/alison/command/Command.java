package alison.command;

import alison.exception.AlisonException;
import alison.tool.*;

public abstract class Command {

    /**
     * Deal with all the changes in Alison's tools.
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @throws AlisonException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException;

    /**
     * Boolean value to determine the execution of Alison.
     * @return True only when it's a Exit Command.
     */
    public boolean isExit() {
        return false;
    }

}
