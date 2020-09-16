package duke.command;

import duke.Duke;

/**
 * ListCommand asks UiResponse to print out current tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        names = new String[] { "list" };
        description = "Lists all current tasks.";
        format = CommandFormat.LIST_CMD_FORMAT;
    }

    /**
     * Asks UiResponse to print out current tasks.
     * @param str any string after command "list" on the same line
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        response(duke);
    }

    private void response(Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportCurrentTasks();
        } else {
            duke.getUiResponse().reportCurrentTasks();
        }
    }
}
