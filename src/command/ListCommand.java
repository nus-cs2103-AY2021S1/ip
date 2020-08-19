package command;

import function.DukeFunction;

public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute(String str) {
        DukeFunction.reportCurrentTasks();
    }
}
