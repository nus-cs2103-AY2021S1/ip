package fei.command;

import fei.exception.FeiException;
import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class StatsCommand extends Command {
    /**
     * Deal with all the changes in Fei's tools.
     *
     * @param tasks   TaskList.
     * @param ui      User Interface.
     * @param storage Storage.
     * @throws FeiException when Command failed to be executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeiException {
        return ui.showStats(tasks);
    }
}
