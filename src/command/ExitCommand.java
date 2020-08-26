package command;

import data.DukeState;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        names = new String[] { "bye" };
    }

    @Override
    public void execute(String str) {
        Ui.reportExit();

        DukeState.exitLoop = true;
    }
}
