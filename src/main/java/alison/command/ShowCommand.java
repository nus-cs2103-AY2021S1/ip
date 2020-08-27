package alison.command;

import alison.tool.*;

public class ShowCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

}
