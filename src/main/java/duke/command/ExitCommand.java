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
        description = "Ends the program.";
        format = CommandFormat.EXIT_CMD_FORMAT;
    }

    /**
     * Ends Duke by changing exitLoop state in DukeState.
     * @param str any string after command "exit" on the same line
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        response(duke);
        duke.getState().endLoop();
    }

    private void response(Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportExit();
        } else {
            duke.getUiResponse().reportExit();
        }
    }
}
