package duke;

/**
 * <p>duke.HelpCommand class defines the behaviour of a command to give user a list of possible commands.</p>
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        ui.replyHelp();
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
