package alison.command;

import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class ShowCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

}
