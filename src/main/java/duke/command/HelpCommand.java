package duke.command;

import duke.Duke;

public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand.
     */
    public HelpCommand() {
        names = new String[] { "help" };
        description = "Lists descriptions of all commands.\nFormat: " + CommandFormat.HELP_CMD_FORMAT;
    }

    @Override
    public void execute(String str, Duke duke) {
        response(duke);
    }

    private void response(Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().showAllCommand(duke.getCommandSet());
        } else {
            duke.getUiResponse().showAllCommand(duke.getCommandSet());
        }
    }
}
