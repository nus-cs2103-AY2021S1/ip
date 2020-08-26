package command;

import data.DukeData;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        names = new String[] { "bye" };
    }

    @Override
    public void execute(String str) {
        Ui.reportExit();

        DukeData.exitLoop = true;
    }
}
