package command;

import function.DukeFunction;
import data.DukeData;

public class ExitCommand extends Command {

    public ExitCommand() {
        names = new String[] { "bye" };
    }

    @Override
    public void execute(String str) {
        DukeFunction.reportExit();

        DukeData.exitLoop = true;
    }
}
