package duke;

/**
 * <p>duke.ListCommand class defines the behaviour of a command to see all tasks in the list.</p>
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyList(taskManager);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
