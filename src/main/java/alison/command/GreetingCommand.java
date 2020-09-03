package alison.command;

import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class GreetingCommand extends Command {

    /**
     * Deal with all the changes in Alison's tools.
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
