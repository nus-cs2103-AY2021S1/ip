package Command;

import Duke.Storage;
import Duke.Ui;
import Friend.FriendList;
import Tasks.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    /**
     * Execute the task.
     * @param tasks
     * @param ui
     * @param storage
     * @return a string of response message.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.exit();
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
     * Check if the command is an exit command.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Check if the command is a friend command
     * @return false
     */
    public boolean isFriendCommand() {
        return false;
    }
}
