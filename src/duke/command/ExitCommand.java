package duke.command;

import duke.Duke;

/**
 * ExitCommand ends Duke by changing exitLoop state in DukeState.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        names = new String[] { "bye" };
    }

    /**
     * Ends Duke by changing exitLoop state in DukeState.
     * @param str any string after command "exit" on the same line
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        duke.ui.reportExit();

        duke.state.exitLoop = true;
    }
}
