package duke.command;

import duke.data.DukeState;
import duke.ui.Ui;

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
