import java.io.IOException;

public class DeleteFriendCommand extends Command {

    private int i;

    /**
     * Constructor for the class.
     * @param i
     */
    public DeleteFriendCommand(int i) {
        this.i = i;
    }

    /**
     * Generate a list of tasks to user.
     * @param friends
     * @param ui
     * @return a String to reply user.
     * @throws IOException
     */
    @Override
    public String execute(FriendList friends, Ui ui) throws IOException {
        Friend deletedFriend = friends.getList().get(this.i - 1);
        friends.delete(this.i - 1);
        return ui.deleteFriend(deletedFriend);
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
