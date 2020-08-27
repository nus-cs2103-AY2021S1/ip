package duke.command;

import duke.Duke;

/**
 * ListCommand asks Ui to print out current tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        names = new String[] { "list" };
    }

    /**
     * Asks Ui to print out current tasks.
     * @param str any string after command "list" on the same line
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        duke.ui.reportCurrentTasks();
    }
}
