package duke.command;

import duke.Duke;

public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute(String str, Duke duke) {
        duke.ui.reportCurrentTasks();
    }
}
