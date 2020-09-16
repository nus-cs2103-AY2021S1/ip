import java.io.IOException;

public class ListCommand extends Command {

    /**
     * Generate a list of tasks to user.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String to reply user.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.generateList(tasks);
    }

    /**
     * Not reference to
     * @param friends
     * @param ui
     * @return null
     * @throws IOException
     */
    @Override
    public String execute(FriendList friends, Ui ui) throws IOException {
        return null;
    }

    /**
     * To show if the programme can end.
     * @return a boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Check if the command is a friend command
     * @return false
     */
    public boolean isFriendCommand() {
        return false;
    }
}
