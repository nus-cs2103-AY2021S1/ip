package alison.command;

import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
