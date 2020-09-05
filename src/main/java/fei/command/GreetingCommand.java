package fei.command;

import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class GreetingCommand extends Command {

    /**
     * Deal with all the changes in Fei's tools.
     *
     * @param tasks   TaskList.
     * @param ui      User Interface.
     * @param storage Storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.greeting();
    }
}
