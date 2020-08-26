package command;

import ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute(String str) {
        Ui.reportCurrentTasks();
    }
}
