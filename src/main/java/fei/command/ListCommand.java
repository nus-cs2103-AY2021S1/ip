package fei.command;

import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

}
