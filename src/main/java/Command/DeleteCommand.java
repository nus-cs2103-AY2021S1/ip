package Command;

import Duke.Storage;
import Duke.Ui;
import Friend.FriendList;
import Tasks.Task;
import Tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int i;

    /**
     * Constructor for the class
     * @param i
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String of the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getList().get(this.i - 1);
        tasks.delete(this.i);
        // delete file in storage
//            storage.saveTask(this.task);
        return ui.deleteTask(deletedTask) + "\n" + tasks.printSize();
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
     * @return false.
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
