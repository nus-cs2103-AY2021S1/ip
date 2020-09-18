import java.util.ArrayList;

/**
 * DeleteCommand is a request to stop Duke from running.
 */

public class ExitCommand extends Command {

    /**
     * No action is required from ExitCommand.
     *
     * @param tasks   TaskList to be modified.
     * @param storage Storage to be activated if there are any changes to TaskList.
     * @return ArrayList containing response message from Duke.
     */
    @Override
    public ArrayList<String> execute(TaskList tasks, Storage storage) {
        return Ui.getGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    public static String toInputString() {
        return "bye";
    }
}
