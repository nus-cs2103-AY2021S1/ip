import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for AddCommand.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String of message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(this.task);
            storage.saveTask(this.task);
            return ui.addTask(this.task) + "\n" + tasks.printSize();
        } catch (IOException e) {
            return e.getMessage();
        }
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
