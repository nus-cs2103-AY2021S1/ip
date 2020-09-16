import java.io.IOException;

public abstract class Command {

    /**
     * Execute the command accordingly.
     * @param tasks
     * @param ui
     * @param storage
     * @return s String of message.
     * @throws IOException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Execute the command accordingly.
     * @param friends
     * @param ui
     * @return s String of message.
     * @throws IOException
     */
    public abstract String execute(FriendList friends, Ui ui) throws IOException;

    /**
     * Check if the command is an exit command
     * @return a boolean value.
     */
    public abstract boolean isExit();

    /**
     * Check if the command is a friend command
     * @return false
     */
    public abstract boolean isFriendCommand();
}
