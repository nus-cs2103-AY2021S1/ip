package duke;

/**
 * <p>duke.ByeCommand class defines the behaviour of a command to end the chat.</p>
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.bye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
