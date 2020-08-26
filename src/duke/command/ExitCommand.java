package duke.command;

import duke.Duke;

public class ExitCommand extends Command {

    public ExitCommand() {
        names = new String[] { "bye" };
    }

    @Override
    public void execute(String str, Duke duke) {
        duke.ui.reportExit();

        duke.state.exitLoop = true;
    }
}
