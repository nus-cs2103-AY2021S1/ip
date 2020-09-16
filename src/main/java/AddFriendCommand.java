import java.io.IOException;

public class AddFriendCommand extends Command{

    private Friend friend;

    /**
     * Constructor for the class.
     * @param friend
     */
    public AddFriendCommand(Friend friend) {
        this.friend = friend;
    }

    /**
     * Generate a list of tasks to user.
     * @param friends
     * @param ui
     * @return a String to reply user.
     * @throws IOException
     */
    public String execute(FriendList friends, Ui ui) throws IOException {
        friends.add(friend);
        return ui.addFriend(this.friend);
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
