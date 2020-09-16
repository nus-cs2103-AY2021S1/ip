import java.io.IOException;

public class FriendListCommand extends Command {

    /**
     * Generate a list of tasks to user.
     * @param friends
     * @param ui
     * @return a String to reply user.
     * @throws IOException
     */
    public String execute(FriendList friends, Ui ui) throws IOException {
        return ui.generateFriendList(friends);
    }

    /**
     * Empty execution.
     * @param tasks
     * @param ui
     * @param storage
     * @return null value as there is no tasks
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
//        return this.execute();
        return null;
    }

    /**
     * To check if the command is an exit command
     * @return a false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Check if the command is a friend command
     * @return true
     */
    public boolean isFriendCommand() {
        return true;
    }
}
